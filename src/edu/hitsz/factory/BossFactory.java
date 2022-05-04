package edu.hitsz.factory;

import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.Main;

/**
 * @author xu
 */
public class BossFactory implements EnemyFactory{
    @Override
    public BossEnemy create(int hpAdded) {
        return new BossEnemy(
                Main.WINDOW_WIDTH / 2,
                (int) (Math.random()*Main.WINDOW_HEIGHT*0.2),
                5,
                0,
                (300 + hpAdded));
    }
}
