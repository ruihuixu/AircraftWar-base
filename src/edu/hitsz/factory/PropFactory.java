package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
/**
 * @author xu
 */
public interface PropFactory {
    /**
     * 抽象方法
     * @param x 输入的初始位置
     * @param y 输入的初始位置
     * @return 道具
     */
    AbstractProp create(int x,int y);
}
