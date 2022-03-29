package factory;

import prop.AbstractProp;
import prop.BombProp;

public class BombFactory implements PropFactory{
    @Override
    public AbstractProp create(int x,int y){
        return new BombProp(x,y,0,0);
    }
}
