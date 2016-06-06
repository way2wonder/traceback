package traceback.synchronization.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yszoe.biz.entity.YakInformation;

import traceback.datasource.DataSource;
import traceback.synchronization.dao.KillRecordsDao;
import traceback.synchronization.dao.PrintRecordDao;
import traceback.synchronization.dao.YakInfoDao;
import traceback.synchronization.model.KillRecord;
import traceback.synchronization.model.PrintRecord;
import traceback.synchronization.model.YakInfo;
import traceback.synchronization.service.SynchronizationService;


@Service("synchronizationService")
@Transactional(propagation = Propagation.SUPPORTS)
public class SynchronizationServiceImpl implements SynchronizationService
{

    @Autowired
    YakInfoDao  yakInfoDao;
    
    @Autowired
    KillRecordsDao  killRecordsDao;
    
    @Autowired
    PrintRecordDao  printRecordDao;
    
    @Override
    //@DataSource("local")
    public List<KillRecord>  queryRecordsByState(String state)
    {
        return killRecordsDao.queryRecordsByState(state);
    }
    
    

    @Override
    public void mergeYakInfo()
    {
        yakInfoDao.callPro("mergeYakInfo()"); 
    }


    @Override
    //@DataSource("server")
    public void uploadButcherData(List<KillRecord> recordList)
    {
        killRecordsDao.saveOrUpdate(recordList);
    }

    
    @Override
    //@DataSource("local")
    public void updateButcherData()
    {
        String hql = "update KillRecord set state = '2' where state='1'";
        killRecordsDao.executeUpdate(hql);
    }



    @Override
    //@DataSource("server")
    public List<YakInfo> queryServerYakInfo()
    {
        String[] fieldArr ={"nh","sex","breedCode","birthday","appearance","herdsmanCode","departCode","outDate","state"};
        StringBuffer sb= new StringBuffer();
        sb.append("select t.nh,")
          .append("       t.sex,")
          .append("       t.breed_code    as breedCode,")
          .append("       t.brithday      as birthday,")
          .append("       t.appearance,")
          .append("       t.herdsman_code as herdsmanCode,")
          .append("       '630001'   as departCode,")
          .append("       t.out_date      as outDate,")
          .append("       t.state         as state")
          .append("  from yak_information t, yak_fluctuate t2")
          .append(" where t.nh = t2.nh")
          .append("   and t2.type = 'TC'")
          .append("   and not exists")
          .append(" (select 1 from T_BUS_KILLRECORDS a where a.nh = t.nh)");
        
        return (List<YakInfo>)yakInfoDao.querySqlList(sb.toString(),YakInfo.class,fieldArr);
    }



    @Override
    //@DataSource("local")
    public void saveTmpDataAndMerge(List<YakInformation> infoList)
    {
        yakInfoDao.saveTempData(infoList);
        yakInfoDao.callPro("mergeYakInfo()");
    }


    @Override
    //@DataSource("local")
    public void updateButcherData(List<KillRecord> recordList)
    {
        killRecordsDao.saveOrUpdate(recordList);
    }



    @SuppressWarnings("unchecked")
    @Override
    //@DataSource("local")
    public List<PrintRecord> queryPrintRecords(String uploadFlagYes)
    {
        String hql = " from PrintRecord where state = '"+uploadFlagYes+"'";
        return (List<PrintRecord>)printRecordDao.queryList(hql);
    }
    
    
    @Override
    //@DataSource("server")
    public void uploadPrintRecordData(List<PrintRecord> recordList)
    {
        printRecordDao.saveOrUpdate(recordList);
    }
    
    
    @Override
    //@DataSource("local")
    public void updatePrintRecordData(List<PrintRecord> recordList)
    {
        printRecordDao.saveOrUpdate(recordList);
    }
}
