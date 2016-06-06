package traceback.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public abstract class AbstractDao<PK extends Serializable, T> {
	
	private final Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao(){
		this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession(){
	    Session result = sessionFactory.getCurrentSession();
	    if(null == result )
	    {
	        result = sessionFactory.openSession();
	    }
	    
	    return result;
	}

	public  void saveOrUpdate(List<T> recordList)
    {
        if (recordList != null && !recordList.isEmpty())
        {
            Session session = getSession();
            Transaction tx = null;
            try
            {
                tx = session.beginTransaction();
                int size = recordList.size();
                for (int i = 0; i < size; i++ )
                {
                    session.saveOrUpdate(recordList.get(i));
                    if (i % 100 == 0)
                    {
                        session.flush();
                        session.clear();
                    }
                }
                
                if(!tx.wasCommitted())
                {
                    tx.commit();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                if (null != tx)
                {
                    tx.rollback();
                }
            }
        }
    }
	
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	protected Criteria createEntityCriteria()
	{
		return getSession().createCriteria(persistentClass);
	}

	
    public int executeUpdate(String hql)
	{
        int result = 0 ;
	    Session session = getSession();
	    Transaction  tx = null;
	    
	    try
        {
          tx = session.beginTransaction();
          Query query = session.createQuery(hql);
          result = query.executeUpdate();
          tx.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            if(tx != null)
            {
                tx.rollback();
            }
        }
	    return result;
	}
    
    
    public List<?> queryList(String hql)
    {
        Query query = this.getSession().createQuery(hql);
        return  query.list();
    }
    
    
    public List<?> querySqlList(String sql,Class<?> c)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        
        if(null != c)
        {
            Field[] fieldArr = c.getDeclaredFields();
            for(Field field :fieldArr)
            {
                query.addScalar(field.getName());
            }
            query.setResultTransformer(Transformers.aliasToBean(c));
        }
        
        return  (List<?>)query.list();
    }
    
    
    
    public List<?> querySqlList(String sql,Class<?> c,String[] fieldArr)
    {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        
        if(null != c)
        {
            query.setResultTransformer(Transformers.aliasToBean(c));
        }
        
        if(null != fieldArr && fieldArr.length>0)
        {
            for(String fieldName :fieldArr)
            {
                if(!StringUtils.isEmpty(fieldName))
                {
                    query.addScalar(fieldName); 
                }
            }
        }
        
        return  (List<?>)query.list();
    }
    
    public void  callPro(String procName)
    {
        //"{Call proc()}"
        String str = "{Call "+procName+"}";
        Session session = this.getSession();   
        SQLQuery query = session.createSQLQuery(str);  
        query.executeUpdate();  
    }
    
}
