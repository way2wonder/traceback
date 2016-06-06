package traceback.synchronization.controller;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yszoe.biz.entity.YakInformation;

import traceback.synchronization.Constants;
import traceback.synchronization.model.KillRecord;
import traceback.synchronization.model.PrintRecord;
import traceback.synchronization.service.SynchronizationService;


/**
 * 同步controller，这个控制器主要是调用WebService，和另一个切换数据源不一样
 * @author zhy
 *
 */
@Controller
public class SyncController
{
    Logger  logger = LogManager.getLogger(SyncController.class);
     
    @Autowired
    SynchronizationService  syncService;
    
    @RequestMapping("/ws-synckillRecords")
    @ResponseBody
    public String syncKillRecords()
    {
        try
        {
            uploadButcherData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return "SUCCESS";
    }
    
    
    public void uploadButcherData()
        throws IOException
    {
        long begin = System.currentTimeMillis();
        logger.info("同步屠宰数据开始！时间："+begin);
        List<KillRecord> recordList = syncService.queryRecordsByState(Constants.UPLOAD_FLAG_YES);

        // axis2 客户端代码
        RPCServiceClient serviceClient = new RPCServiceClient();

        Options options = serviceClient.getOptions();

        EndpointReference targetEPR = new EndpointReference(
            "http://10.0.0.53:8089/services/traceback");
        options.setTo(targetEPR);

        QName uploadKillRecord = new QName("http://traceback.axis.yszoe.com", "uploadKillRecords");

        // 序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(recordList);
        Object[] args = new Object[] {baos.toByteArray()};

        boolean flag = (boolean)serviceClient.invokeBlocking(uploadKillRecord, args,
            new Class[] {boolean.class})[0];

        if (flag)
        {
            if (null != recordList && !recordList.isEmpty())
            {
                for (KillRecord record : recordList)
                {
                    if (null != record)
                    {
                        record.setState("2");
                    }
                }
            }
            syncService.updateButcherData(recordList);
        }
        
        long end = System.currentTimeMillis();
        logger.info("同步屠宰数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
    }
    
    
    public List<YakInformation> downloadYakInfo() throws IOException, ClassNotFoundException
    {
        long begin = System.currentTimeMillis();
        logger.info("下载牛只基本信息数据开始！时间："+begin);
        // axis2 客户端代码
        RPCServiceClient serviceClient = new RPCServiceClient();

        Options options = serviceClient.getOptions();

        EndpointReference targetEPR = new EndpointReference(
                "http://10.0.0.53:8089/services/traceback");
        options.setTo(targetEPR);
        
        QName uploadKillRecord =
            new QName("http://traceback.axis.yszoe.com", "getYakInfoList");
        
        byte[] byteArr= (byte[])serviceClient.invokeBlocking(uploadKillRecord, new Object[]{},new Class[]{byte[].class})[0];
        
        ObjectInputStream  ois = new ObjectInputStream(new ByteArrayInputStream(byteArr));
        List<YakInformation> infoList = (List<YakInformation>)ois.readObject();
        
        long end = System.currentTimeMillis();
        logger.info("下载牛只基本信息结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
        return infoList;
    }
    
    
    @RequestMapping("/ws-syncPrintRecord")
    @ResponseBody
    public String uploadPrintRecord() throws IOException
    {
        String result = "";
        List<PrintRecord>  recordList = syncService.queryPrintRecords(Constants.UPLOAD_FLAG_YES);

        // axis2 客户端代码
        RPCServiceClient serviceClient = new RPCServiceClient();

        Options options = serviceClient.getOptions();

        EndpointReference targetEPR = new EndpointReference(
            "http://10.0.0.53:8089/services/traceback");
        options.setTo(targetEPR);

        QName uploadKillRecord = new QName("http://traceback.axis.yszoe.com", "uploadPrintRecords");

        // 序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(recordList);
        Object[] args = new Object[] {baos.toByteArray()};

        boolean flag = (boolean)serviceClient.invokeBlocking(uploadKillRecord, args,
            new Class[] {boolean.class})[0];

        if (flag)
        {
            result ="上传打印日至数据成功！";
            if (null != recordList && !recordList.isEmpty())
            {
                for (PrintRecord record : recordList)
                {
                    if (null != record)
                    {
                        record.setState("2");
                    }
                }
                
            }
            //更新本地数据状态为2
            syncService.updatePrintRecordData(recordList);
            result +="更新本地数据状态成功!";
        }
        
        return result;
    }
    
    
    @RequestMapping("/ws-sync")
    @ResponseBody
    public String sync()  
    {
        long begin = System.currentTimeMillis();
        logger.info("手动同步数据开始！时间："+begin);
        try
        {
            //先上传屠宰信息
            uploadButcherData();
            //然后下载牛只信息
            List<YakInformation>  infoList = downloadYakInfo();
            //然后插到临时表再调用存储过程
            syncService.saveTmpDataAndMerge(infoList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "Fail,IO 异常";
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return "Fail,ClassNotFoundException";
        }
        
        long end = System.currentTimeMillis();
        logger.info("手动同步数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
        return "SUCCESS";
        
    }
    
    
    
    @Scheduled(cron="5 0/10 * * * * ")
    public void syncData()
    {
        sync(); 
    }
}
