package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
import edu.hitsz.prop.BombProp;
/**
 * @author xu
 */
public class BombFactory implements PropFactory{
    @Override
    public AbstractProp create(int x,int y){
        return new BombProp(x,y,0,5);
    }
}
