package edu.hitsz.application.game;

/**
 * @author 一徐
 */
public class HardGame extends Game{
    @Override
    protected void initialise() {
        this.bossNeeded = true;
        this.enemyMaxNumber = 7;
        this.eliteChance = 0.35;
        this.bossLimit = 200;
        this.cycleDuration = 300;
    }

    @Override
    protected int getHpAdded(int bossNumber) {
        return 300*bossNumber;
    }

    @Override
    protected double getDifficulty(int time) {
        double difficulty = ((double)time)/10000 + 1;
        System.out.println("难度增加,当前难度系数为"+difficulty+",精英机出现概率提升为"+(eliteChance*difficulty)+
                ",精英机与普通机血量增加"+(int)(difficulty*30-30));
        return difficulty;
    }
}
