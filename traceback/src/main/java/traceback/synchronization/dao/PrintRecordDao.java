package traceback.synchronization.dao;

import org.springframework.stereotype.Repository;

import traceback.common.AbstractDao;
import traceback.synchronization.model.PrintRecord;

@Repository("printRecordDao")
public class PrintRecordDao  extends  AbstractDao<Long, PrintRecord>
{
    
}
