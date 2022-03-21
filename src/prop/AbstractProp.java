package prop;

import edu.hitsz.basic.AbstractFlyingObject;

public class AbstractProp extends AbstractFlyingObject {
    /**
     * 标志道具种类，blood--1,bomb--2,fire--3
     */
    protected int propkind;

    public AbstractProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }

    public int getPropkind() {
        return propkind;
    }
}
