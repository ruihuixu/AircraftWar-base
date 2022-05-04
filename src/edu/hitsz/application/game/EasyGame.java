package edu.hitsz.application.game;

/**
 * @author 一徐
 */
public class EasyGame extends Game{
    @Override
    protected void initialise() {
        this.bossNeeded = false;
        this.enemyMaxNumber = 3;
        this.eliteChance = 0.15;
        this.bossLimit = 600;
        this.cycleDuration = 800;
    }

    @Override
    protected int getHpAdded(int bossNumber) {
        return 0;
    }

    @Override
    double getDifficulty(int time) {
        return 1;
    }
}
