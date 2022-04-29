package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import strategy.ShootContext;
import strategy.ShootStrategy;
import strategy.StraightShoot;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {
    /**
     * 单例模式
     */
    private volatile static HeroAircraft heroAircraft;

    public static  HeroAircraft getInstance(){
        if(heroAircraft==null){
            synchronized (HeroAircraft.class){
                if(heroAircraft==null){
                    heroAircraft = new HeroAircraft(
                            Main.WINDOW_WIDTH / 2,
                            Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                            0,
                            0,
                            100);
                }
            }
        }
        return heroAircraft;
    }

    private ShootStrategy strategy;

    public void setStrategy(ShootStrategy strategy){
        this.strategy = strategy;
    }
    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp    初始生命值
     */
    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.kind = 0;
        this.shootNum = 1;
        this.direction = -1;
        this.power = 30;
        this.strategy = new StraightShoot();
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
        ShootContext context = new ShootContext(strategy);
        return context.executeStrategy(aircraft);
    }

}
