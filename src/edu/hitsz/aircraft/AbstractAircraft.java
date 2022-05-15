package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.basic.AbstractFlyingObject;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    protected int direction;
    /**
     * 子弹一次发射数量
     */
    protected int shootNum;
    /**
     * 子弹伤害
     */
    protected int power;
    /**
     * 飞机类型，1--普通敌机，2--精英敌机，3--boss敌机，0--英雄机
     */
    protected  int kind;

    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
    }

    public void decreaseHp(int decrease){
        hp -= decrease;
        if(hp <= 0){
            hp=0;
            vanish();
        }
    }

    public void increaseHp(int increase){
        hp += increase;
        if(hp>=maxHp){
            hp = maxHp;
        }
    }

    public int getHp() {
        return hp;
    }
    public int getShootNum(){return shootNum;}
    public int getDirection(){return direction;}
    public int getPower(){return power;}
    public int getKind(){return kind;}

    public void setShootnum(int shootNum){this.shootNum = shootNum;}

    /**
     * 射击方法
     * @param aircraft
     * @return 子弹列
     */
    public abstract List<BaseBullet> shoot(AbstractAircraft aircraft);

}


