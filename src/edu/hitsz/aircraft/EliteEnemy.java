package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.application.Main;
import edu.hitsz.factory.BloodFactory;
import edu.hitsz.factory.BombFactory;
import edu.hitsz.factory.FireFactory;
import edu.hitsz.factory.PropFactory;
import edu.hitsz.prop.AbstractProp;
import strategy.ShootContext;
import strategy.StraightShoot;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xu
 */
public class EliteEnemy extends AbstractEnemy {
    /**
     * kind标志飞机种类
     * @return 数字0、1、2、3分别标志英雄机、普通敌机、精英敌机、boss敌机
     */
    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.kind = 2;
        this.shootNum = 1;
        this.power = 10;
        this.direction = 1;
    }

    @Override
    public List<AbstractProp> addProp(AbstractEnemy abstractEnemy) {
        List<AbstractProp> res = new LinkedList<>();
        PropFactory propFactory;
        double[] rate = {0.9,0.45,0.6};
        if(Math.random()<rate[0]){
            int x = abstractEnemy.getLocationX();
            int y = abstractEnemy.getLocationY();
            double r = Math.random();
            if(r<rate[1]){
                propFactory = new BloodFactory();
                res.add(propFactory.create(x,y));
            }else if(r>rate[2]){
                propFactory = new FireFactory();
                res.add(propFactory.create(x,y));
            }else{
                propFactory = new BombFactory();
                res.add(propFactory.create(x,y));
            }
        }
        return res;
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }


    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        ShootContext context = new ShootContext(new StraightShoot());
        return context.executeStrategy(aircraft);
    }
}
