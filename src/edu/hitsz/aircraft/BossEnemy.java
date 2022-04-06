package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;
/**
 * @author xu
 */
public class BossEnemy extends AbstractAircraft{
    /**
     * 子弹一次发射数量
     */
    private final int shootNum = 1;
    /**
     * 子弹伤害
     */
    private int power = 10;
    /**
     * 子弹发射方向：向下——-1，向上——1
     */
    private int direction = -1;

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.kind = 3;
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() - direction*2;
        int speedX = 0;
        int speedY = this.getSpeedY() - direction*5;
        BaseBullet abstractBullet;
        for(int i=0; i<shootNum; i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, speedX, speedY, power);
            res.add(abstractBullet);
        }
        return res;
    }
}
