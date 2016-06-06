package traceback.synchronization.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import traceback.synchronization.Constants;
import traceback.synchronization.model.KillRecord;
import traceback.synchronization.model.PrintRecord;
import traceback.synchronization.model.YakInfo;
import traceback.synchronization.service.SynchronizationService;

@Deprecated
@Controller
public class SynchronizationController
{
    Logger  logger = LogManager.getLogger(SynchronizationController.class);
     
    @Autowired
    SynchronizationService  syncService;
    
//    @Scheduled(cron = "5 0/5 * * * ?")
    public void run()
    {
        long begin = System.currentTimeMillis();
        logger.info("同步数据开始！时间："+begin);
        
        // 先向服务器同步屠宰数据,完成后将所有的已经上传的记录更新成状态2
       uploadButcherData();
        // 然后下载待屠宰数据到临时表
       //downloadYakInfo();
      
        long end = System.currentTimeMillis();
        logger.info("同步数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
    }
    
    @RequestMapping("/sync")
    @ResponseBody
    public String sync()
    {
        long begin = System.currentTimeMillis();
        logger.info("同步数据开始！时间："+begin);
        
        // 先向服务器同步屠宰数据,完成后将所有的已经上传的记录更新成状态2
       uploadButcherData();
        // 然后下载待屠宰数据到临时表
       //downloadYakInfo();
      
        long end = System.currentTimeMillis();
        logger.info("同步数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
        return "SUCCESS";
    }
    
    @RequestMapping("/syncKillRecords")
    @ResponseBody
    public String syncKillRecords()
    {
        long begin = System.currentTimeMillis();
        logger.info("同步数据开始！时间："+begin);
        
        // 先向服务器同步屠宰数据,完成后将所有的已经上传的记录更新成状态2
        uploadButcherData();
      
        long end = System.currentTimeMillis();
        logger.info("同步数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
        return "SUCCESS";
    }
    
    
    
    @RequestMapping("/syncPrintRecord")
    @ResponseBody
    public String syncPrintRecords()
    {
        long begin = System.currentTimeMillis();
        logger.info("上传打印日志数据开始！时间："+begin);
        List<PrintRecord>  recordList = syncService.queryPrintRecords(Constants.UPLOAD_FLAG_YES);
        
        syncService.uploadPrintRecordData(recordList);
        
        if(null != recordList && !recordList.isEmpty())
        {
            for(PrintRecord record : recordList)
            {
               if(null != record)
               {
                   record.setState("2");
               }
            }
        }
        syncService.updatePrintRecordData(recordList);
        
        long end = System.currentTimeMillis();
        logger.info("上传打印日志数据结束！时间："+System.currentTimeMillis()+"耗时："+(end-begin)/1000+"秒");
        return "SUCCESS";
    }
    
    public void uploadButcherData()
    {
        List<KillRecord>  recordList =  syncService.queryRecordsByState(Constants.UPLOAD_FLAG_YES);
        syncService.uploadButcherData(recordList);
        
        if(null != recordList && !recordList.isEmpty())
        {
            for(KillRecord record : recordList)
            {
               if(null != record)
               {
                   record.setState("2");
               }
            }
        }
        syncService.updateButcherData(recordList);
    }
    
//    public void downloadYakInfo()
//    {
//       List<YakInfo> infoList =  syncService.queryServerYakInfo();
//       syncService.saveTmpDataAndMerge(infoList);
//    }
}
