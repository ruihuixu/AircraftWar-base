package edu.hitsz.application.game;

/**
 * @author 一徐
 */
public class NormalGame extends Game{
    @Override
    protected void initialise() {
        this.bossNeeded = true;
        this.enemyMaxNumber = 5;
        this.eliteChance = 0.25;
        this.bossLimit = 400;
        this.cycleDuration = 500;
    }

    @Override
    protected int getHpAdded(int bossNumber) {
        return 120*bossNumber;
    }

    @Override
    protected double getDifficulty(int time) {
        double difficulty = ((double)time)/20000 + 1;
        System.out.println("难度增加,当前难度系数为 "+difficulty+",精英机出现概率提升为"+(eliteChance*difficulty)+
                ",精英机与普通机血量增加"+(int)(difficulty*30-30));
        return difficulty;
    }
}
