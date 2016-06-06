package traceback.synchronization.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import traceback.common.AbstractDao;
import traceback.common.Pager;
import traceback.scrollScreen.model.ActionDisplay;
import traceback.synchronization.model.KillRecord;


@Repository("killRecordsDao")
public class KillRecordsDao  extends  AbstractDao<String, KillRecord>
{

    @SuppressWarnings("unchecked")
    public List<KillRecord> queryRecordsByState(String uploadFlag)
    {
       Criteria criteria = createEntityCriteria();
       criteria.add(Restrictions.eq("state", uploadFlag));
       return  (List<KillRecord>) criteria.list();
    }


    @SuppressWarnings("unchecked")
    public List<ActionDisplay>  queryActions(Pager pager,String sql,String countSql,Map<String,Object> params)
    {
        Session session = this.getSession();
        SQLQuery query = session.createSQLQuery(sql);
        query.setFirstResult(pager.getFromIndex());
        query.setMaxResults(pager.getEachPageRows());
        query.setResultTransformer(Transformers.aliasToBean(ActionDisplay.class));
       
        
        //修改pager对象
        Query countQuery = session.createSQLQuery(countSql);
        Long countResults = ((BigInteger) countQuery.uniqueResult()).longValue();
        pager.setTotalRows(countResults);
        
        if(params != null)
        {
            Set<String>  keySet = params.keySet();
            for(String key :keySet)
            {
                query.setParameter(key,params.get(key));
            }
        }
        return query.list();
    }


   
}
