package edu.hitsz.factory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.BossEnemy;

/**
 * @author xu
 */
public class BossFactory implements EnemyFactory{
    @Override
    public AbstractAircraft create() {
        return new BossEnemy(0,0,0,0,300);
    }
}
