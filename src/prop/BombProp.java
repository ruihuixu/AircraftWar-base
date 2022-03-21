package prop;

public class BombProp extends AbstractProp{
    /**
     * 标志道具种类
     */
    private int propkind = 2;

    public BombProp(int  locationX, int locationY, int speedX, int speedY){
        super(locationX, locationY, speedX, speedY);
    }
}
