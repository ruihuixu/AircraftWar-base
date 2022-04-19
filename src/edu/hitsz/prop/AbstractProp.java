package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * @author xu
 */
public abstract class AbstractProp extends AbstractFlyingObject {
    /**
     * 标志道具种类，blood--1,bomb--2,fire--3
     */
    protected int propkind;

    public AbstractProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    /**
     * 道具发挥作用
     * @param heroAircraft
     */
    public abstract void work(HeroAircraft heroAircraft);

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴出界
        if (speedY > 0 && locationY >= Main.WINDOW_HEIGHT ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    public int getPropkind() {
        return propkind;
    }

}
