package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;

/**
 * @author xu
 */
public class BloodProp extends AbstractProp{
    public BloodProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.propkind = 1;
    }

    @Override
    public void work(HeroAircraft heroAircraft) {
        heroAircraft.increaseHp(30);
    }

}
