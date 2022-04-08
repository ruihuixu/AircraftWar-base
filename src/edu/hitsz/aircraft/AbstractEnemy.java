package edu.hitsz.aircraft;

import edu.hitsz.prop.AbstractProp;

import java.util.List;

/**
 * @author 一徐
 */
public abstract class AbstractEnemy extends AbstractAircraft{
    public AbstractEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    /**
     * 生成道具
     * @param abstractEnemy 敌机
     * @return 精英机生成道具，其余可空返回
     */
    public abstract List<AbstractProp> addProp(AbstractEnemy abstractEnemy);
}
