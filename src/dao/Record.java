package dao;

import java.io.Serializable;

/**
 * @author 一徐
 */
public class Record implements Serializable {
    private String name;
    private String time;
    private int score;

    public Record(String time,int score) {
        this.time = time;
        this.score = score;
        this.name = "testUserName";
    }

    public void setName(String name){
        this.name = name;
    }
    public void setTime(String time){this.time = time;}
    public void setScore(int score){this.score = score;}
    public String getTime(){
        return time;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
}
