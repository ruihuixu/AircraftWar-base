package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import strategy.ScatterShoot;
import strategy.StraightShoot;

/**
 * @author xu
 */
public class FireProp extends AbstractProp{
    public FireProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.propkind = 3;
    }

    @Override
    public void work(HeroAircraft heroAircraft) {
        Runnable runnable =()->{
            try {
                Thread.sleep(5000);
                heroAircraft.setShootnum(1);
                heroAircraft.setStrategy(new StraightShoot());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        System.out.println("FireSupply active!");
        if(Main.soundEffect){
            new MusicThread("src/videos/bullet.wav").start();
        }
        heroAircraft.setShootnum(3);
        heroAircraft.setStrategy(new ScatterShoot());
        new Thread(runnable).start();
    }

}
