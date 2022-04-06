package edu.hitsz.prop;
/**
 * @author xu
 */
public class BombProp extends AbstractProp{
    public BombProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
        this.propkind = 2;
    }
}
