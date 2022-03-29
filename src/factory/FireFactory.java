package factory;

import prop.AbstractProp;
import prop.FireProp;

public class FireFactory implements PropFactory{
    @Override
    public AbstractProp create(int x, int y) {
        return new FireProp(x,y,0,0);
    }
}
