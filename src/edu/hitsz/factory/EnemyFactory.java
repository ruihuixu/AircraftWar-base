package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.AbstractEnemy;

/**
 * @author xu
 */
public interface EnemyFactory {
    /**
     * 抽象方法
     * @return 敌机
     */
    AbstractEnemy create();
}
