package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 一徐
 */
public class RecordDaoImpl implements RecordDao{
    private final List<Record> records;

    public RecordDaoImpl() {
        records = new ArrayList<>();
    }

    @Override
    public List<Record> getAllRecords() {
        return records;
    }

    @Override
    public void doAdd(Record record) {
        records.add(record);
    }

    @Override
    public void ranking() {
        Compare temp = new Compare();
        records.sort(temp);
    }

    public void store() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/dao/scoreRanking"));
        oos.writeObject(records);
        oos.flush();
        oos.close();
    }

    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/dao/scoreRanking"));
        ArrayList<Record> temp = (ArrayList<Record>) ois.readObject();
        records.addAll(temp);
        ois.close();
    }
}
