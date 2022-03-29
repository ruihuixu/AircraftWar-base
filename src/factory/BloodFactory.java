package factory;

import prop.AbstractProp;
import prop.BloodProp;

public class BloodFactory implements PropFactory{
    @Override
    public AbstractProp create(int x,int y) {
        return new BloodProp(x,y,0,0);
    }
}
