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
    }
}
