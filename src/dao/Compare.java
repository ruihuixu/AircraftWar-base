package dao;

import java.util.Comparator;

/**
 * @author 一徐
 */
public class Compare implements Comparator<Record> {
    @Override
    public int compare(Record t1, Record t2) {
        if(t1.getScore()> t2.getScore()){
            return -1;
        }else {
            return 1;
        }
    }
}
