package dao;

import java.util.List;

/**
 * @author 一徐
 */
public interface RecordDao {
    /**
     * 得到所有记录
     * @return
     */
    List<Record> getAllRecords();
    /**
     * 添加一条记录
     * @param record
     */
    void doAdd(Record record);
    /**
     * 将已有的记录排名
     */
    void ranking();
}
