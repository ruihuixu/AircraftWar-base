package edu.hitsz.factory;

import edu.hitsz.prop.AbstractProp;
/**
 * @author xu
 */
public interface PropFactory {
    /**
     * 抽象方法
     * @param x 生成处坐标
     * @param y 生成处坐标
     * @return 道具
     */
    AbstractProp create(int x,int y);
}
