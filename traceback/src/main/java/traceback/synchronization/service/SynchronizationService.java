package traceback.synchronization.service;

import java.util.List;

import com.yszoe.biz.entity.YakInformation;

import traceback.synchronization.model.KillRecord;
import traceback.synchronization.model.PrintRecord;
import traceback.synchronization.model.YakInfo;

public interface SynchronizationService
{
    /**
     * 查询服务器上满足条件的待宰的牛只信息
     * @return
     */
    List<YakInfo> queryServerYakInfo();
    
    /**
     * @param infoList  将数据保存到临时表中
     */
    void saveTmpDataAndMerge(List<YakInformation> infoList);

    void mergeYakInfo();

    List<KillRecord> queryRecordsByState(String state);
    
    void uploadButcherData(List<KillRecord>  recordList);

    /**
     * 更新屠宰信息,同步完成后将已经屠宰完成的记录的状态改成2
     * @param recordList
     */
    void updateButcherData();
    
    void updateButcherData(List<KillRecord>  recordList);

    List<PrintRecord> queryPrintRecords(String uploadFlagYes);

    void uploadPrintRecordData(List<PrintRecord> recordList);

    void updatePrintRecordData(List<PrintRecord> recordList);

}
