package edu.hitsz.prop;

import edu.hitsz.aircraft.HeroAircraft;
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
        heroAircraft.setShootnum(3);
        heroAircraft.setStrategy(new ScatterShoot());
    }

}
