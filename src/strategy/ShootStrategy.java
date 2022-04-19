package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

/**
 * @author 一徐
 */
public interface ShootStrategy {
    /**
     * 子弹弹道策略
     * @param aircraft
     * @return 子弹列
     */
    List<BaseBullet> shootWay(AbstractAircraft aircraft);
}
