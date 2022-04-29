package dao;

import edu.hitsz.application.Main;

import java.io.*;
import java.util.ArrayList;

/**
 * @author 一徐
 */
public class RecordDaoImpl implements RecordDao{
    private final ArrayList<Record> records;

    public RecordDaoImpl() {
        records = new ArrayList<>();
    }

    @Override
    public ArrayList<Record> getAllRecords() {
        return records;
    }

    @Override
    public void doAdd(Record record) {
        records.add(record);
    }

    @Override
    public void doDelete(int i){
        records.remove(records.get(i));
    }

    @Override
    public void ranking() {
        Compare temp = new Compare();
        records.sort(temp);
    }

    public void store() throws IOException {
        ObjectOutputStream oos;
        switch(Main.gameMode){
            case EASY:
                oos = new ObjectOutputStream(new FileOutputStream("src/dao/EASY"));
                break;
            case NORMAL:
                oos = new ObjectOutputStream(new FileOutputStream("src/dao/NORMAL"));
                break;
            case HARD:
                oos = new ObjectOutputStream(new FileOutputStream("src/dao/HARD"));
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        oos.writeObject(records);
        oos.flush();
        oos.close();
    }

    public void read() throws IOException, ClassNotFoundException {
        ObjectInputStream ois;
        switch(Main.gameMode){
            case EASY:
                ois = new ObjectInputStream(new FileInputStream("src/dao/EASY"));
                break;
            case NORMAL:
                ois = new ObjectInputStream(new FileInputStream("src/dao/NORMAL"));
                break;
            case HARD:
                ois = new ObjectInputStream(new FileInputStream("src/dao/HARD"));
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        ArrayList<Record> temp = (ArrayList<Record>) ois.readObject();
        records.addAll(temp);
        ois.close();
    }
}
