package dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 一徐
 */
public interface RecordDao {
    /**
     * 得到所有记录
     * @return
     */
    ArrayList<Record> getAllRecords();
    /**
     * 添加一条记录
     * @param record
     */
    void doAdd(Record record);
    /**
     * 删除一条记录
     * @param i 行序号
     */
    void doDelete(int i) throws IOException, ClassNotFoundException;
    /**
     * 将已有的记录排名
     */
    void ranking();
}
