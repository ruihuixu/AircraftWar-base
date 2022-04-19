package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 一徐
 */
public class ShootContext {
    private ShootStrategy strategy;

    public ShootContext(ShootStrategy strategy){
        this.strategy = strategy;
    }

    public void setStrategy(ShootStrategy strategy) {
        this.strategy = strategy;
    }
    public List<BaseBullet> executeStrategy(AbstractAircraft aircraft){
        return strategy.shootWay(aircraft);
    }
}
