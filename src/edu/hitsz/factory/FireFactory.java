package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.FireProp;
/**
 * @author xu
 */
public class FireFactory implements PropFactory{
    @Override
    public AbstractProp create(int x, int y) {
        return new FireProp(x,y,0,5);
    }
}
