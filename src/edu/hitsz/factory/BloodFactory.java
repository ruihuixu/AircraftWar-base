package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BloodProp;
/**
 * @author xu
 */
public class BloodFactory implements PropFactory{
    @Override
    public AbstractProp create(int x,int y) {
        return new BloodProp(x,y,0,5);
    }
}
