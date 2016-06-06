package traceback.synchronization.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.yszoe.biz.entity.YakInformation;

import traceback.common.AbstractDao;
import traceback.synchronization.model.YakInfo;


@Repository("yakInfoDao")
public class YakInfoDao  extends  AbstractDao<String, YakInfo>
{
  
    static final  String TEMP_TABLE_NAME = "TEMP_YAK_INFO";
    public  void saveTempData(List<YakInformation> infoList)
    {
        String sql = "replace  into  "+TEMP_TABLE_NAME+"(NH, SEX, BREED_CODE, BIRTHDAY, APPEARANCE, HERDSMAN_CODE, DEPART_CODE, STATE, OUT_DATE) values(?,?,?,?,?,?,?,?,?)";
        Session session = this.getSession();
        
        Transaction  tx = session.beginTransaction();
        
        SQLQuery  query = session.createSQLQuery(sql);
        
        //创建临时表,先drop再建
        SQLQuery  tmpTableQuery = session.createSQLQuery("drop table if exists "+TEMP_TABLE_NAME);
        tmpTableQuery.executeUpdate();
        tmpTableQuery = session.createSQLQuery("create TEMPORARY table "+TEMP_TABLE_NAME+" as select * from yak_infomation where 1=2");
        tmpTableQuery.executeUpdate();
        
        int i=0;
        
        for(YakInformation info:infoList)
        {
            i++;
            if(null !=info)
            {
                query.setString(0, info.getNh());
                query.setString(1, info.getSex());
                query.setString(2, info.getBreedCode());
                query.setDate(3, info.getBrithday());
                query.setString(4, info.getAppearance());
                query.setString(5, info.getHerdsmanCode());
                query.setString(6, info.getDepartCode());
                query.setString(7, info.getState());
                query.setDate(8, info.getOutDate());
                query.executeUpdate();
            }
            
            if(i%100 == 0)
            {
                session.flush();
                session.clear();
            }
        }
        
        tx.commit();
    }
}
