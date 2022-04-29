package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.application.Main;
import edu.hitsz.prop.AbstractProp;
import strategy.ScatterShoot;
import strategy.ShootContext;

import java.util.LinkedList;
import java.util.List;
/**
 * @author xu
 */
public class BossEnemy extends AbstractEnemy{
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.kind = 3;
        this.power = 10;
        this.shootNum = 5;
        this.direction = 1;
    }

    @Override
    public List<AbstractProp> addProp(AbstractEnemy abstractEnemy) {
        return new LinkedList<>();
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
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        ShootContext context = new ShootContext(new ScatterShoot());
        return context.executeStrategy(aircraft);
    }
}
