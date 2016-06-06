package traceback.scrollScreen.service;

import java.util.List;
import java.util.Map;

import traceback.common.Pager;
import traceback.scrollScreen.model.ActionDisplay;
import traceback.synchronization.model.KillRecord;

public interface KillRecordService
{
    List<ActionDisplay> queryActions(Pager pager,Map<String,Object> paramMap);

    List<ActionDisplay> queryAllActions();
    
    List<KillRecord>  queryKillRecord();
}
