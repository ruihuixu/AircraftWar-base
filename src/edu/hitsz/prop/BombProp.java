package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

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

    }
}


