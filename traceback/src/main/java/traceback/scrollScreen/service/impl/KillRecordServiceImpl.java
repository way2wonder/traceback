package traceback.scrollScreen.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import traceback.common.Pager;
import traceback.scrollScreen.model.ActionDisplay;
import traceback.scrollScreen.service.KillRecordService;
import traceback.synchronization.dao.KillRecordsDao;
import traceback.synchronization.model.KillRecord;


@Service("killRecordService")
@Transactional
public class KillRecordServiceImpl   implements   KillRecordService
{

    
    @Autowired
    KillRecordsDao  recordsDao; 
   
    @Override
    public List<ActionDisplay> queryActions(Pager pager,Map<String,Object> paramMap)
    {
        
      StringBuffer sb = new StringBuffer();
      sb.append("select * from (")
        .append("select t.nh,t.KILL_TIME  as actionTime,'屠宰' as actionName from t_bus_killrecords t  where date_format(t.KILL_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
        .append(" UNION ALL ")
        .append("select t.nh,t.COARSE_SEGMENT_TIME as actionTime,'粗分割' as actionName from t_bus_killrecords t  where date_format(t.COARSE_SEGMENT_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
        .append(" UNION ALL ")
        .append("select t.nh,t.BONE_PACK_TIME  as actionTime,'带骨肉称重包装' as actionName from t_bus_killrecords t  where date_format(t.BONE_PACK_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
        .append(" UNION ALL ")
        .append("select t.nh,t.LEG_PACK_TIME  as actionTime,'腿肉称重包装'  as actionName from t_bus_killrecords t  where date_format(t.LEG_PACK_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
        .append(") a order by actionTime desc");
      
        
        String countSql= sb.toString().replaceFirst("\\*", " count(1) "); 
        
        return recordsDao.queryActions(pager,sb.toString(),countSql,paramMap);
    }

    public KillRecordsDao getRecordsDao()
    {
        return recordsDao;
    }

    public void setRecordsDao(KillRecordsDao recordsDao)
    {
        this.recordsDao = recordsDao;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ActionDisplay> queryAllActions()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("select * from (")
          .append("select t.nh,t.KILL_TIME  as actionTime,'屠宰' as actionName from t_bus_killrecords t  where date_format(t.KILL_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
          .append(" UNION ALL ")
          .append("select t.nh,t.COARSE_SEGMENT_TIME as actionTime,'粗分割' as actionName from t_bus_killrecords t  where date_format(t.COARSE_SEGMENT_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
          .append(" UNION ALL ")
          .append("select t.nh,t.BONE_PACK_TIME  as actionTime,'带骨肉称重包装' as actionName from t_bus_killrecords t  where date_format(t.BONE_PACK_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
          .append(" UNION ALL ")
          .append("select t.nh,t.LEG_PACK_TIME  as actionTime,'腿肉称重包装'  as actionName from t_bus_killrecords t  where date_format(t.LEG_PACK_TIME,'%Y-%m-%d') = date_format(NOW(),'%Y-%m-%d')")
          .append(") a order by actionTime desc");
        return (List<ActionDisplay>)recordsDao.querySqlList(sb.toString(), ActionDisplay.class);
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public List<KillRecord> queryKillRecord()
    {
        StringBuffer sb = new StringBuffer();
      sb.append(" from KillRecord  t where DATE_FORMAT(t.bonePackTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
        .append("or  DATE_FORMAT(t.killTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
        .append("or  DATE_FORMAT(t.legPackTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
        .append("or  DATE_FORMAT(t.coarseSegmentTime,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d')")
        .append(" order by GREATEST(IFNULL( t.bonePackTime,0),IFNULL(t.killTime,0),IFNULL(t.legPackTime,0),IFNULL(t.coarseSegmentTime,0)) desc");
        return (List<KillRecord>)recordsDao.queryList(sb.toString());
    }
}
