package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.application.MusicThread;

/**
 * @author xu
 */
public class BombProp extends AbstractProp {
    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
        this.propkind = 2;
    }

    @Override
    public void work(HeroAircraft heroAircraft) {
        System.out.println("BombSupply active!");
        if (Main.soundEffect){
            new MusicThread("src/videos/bomb_explosion.wav").start();
        }
    }
}


