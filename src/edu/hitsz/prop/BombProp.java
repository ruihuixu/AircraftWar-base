package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.Observer;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

import java.util.ArrayList;

/**
 * @author xu
 */
public class BombProp extends AbstractProp {
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        this.propkind = 2;
    }

    public void notifyObserver() {
        //遍历观察者集合，调用每一个观察者的响应方法
        for(Object obs:observers) {
            ((Observer)obs).response();
        }
    }

    @Override
    public void work(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
        if (Main.soundEffect){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
        notifyObserver();
    }

    /**
     * 定义一个观察者集合用于存储所有观察者对象
     */
    protected ArrayList<Observer> observers = new ArrayList();
    /**
     * 注册方法，用于向观察者集合中增加一个观察者
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }
    /**
     * 注销方法，用于在观察者集合中删除一个观察者
     * @param observer
     */
    public void detach(Observer observer) {
        observers.remove(observer);
    }
}


