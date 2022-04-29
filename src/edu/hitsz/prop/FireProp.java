package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;
import strategy.ScatterShoot;

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
        System.out.println("FireSupply active!");
        if(Main.soundEffect){
            new MusicThread("src/videos/bullet.wav").start();
        }
        heroAircraft.setShootnum(3);
        heroAircraft.setStrategy(new ScatterShoot());
    }

}
