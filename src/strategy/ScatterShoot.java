package strategy;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xu
 */
public class ScatterShoot implements ShootStrategy{
    @Override
    public List<BaseBullet> shootWay(AbstractAircraft aircraft) {
        List<BaseBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
        int shootNum = aircraft.getShootNum();
        int power = aircraft.getPower();
        BaseBullet baseBullet;
        for(int i=0;i<shootNum;i++){
            if(aircraft.getKind()==0){
                baseBullet = new HeroBullet(x + (i*2 - shootNum + 1)*10, y, (i*2 - shootNum + 1)*1, speedY, power);
                res.add(baseBullet);
            }else {
                baseBullet = new EnemyBullet(x + (i*2 - shootNum + 1)*10, y, (i*2 - shootNum + 1)*1, speedY, power);
                res.add(baseBullet);
            }
        }
        return res;
    }
}
