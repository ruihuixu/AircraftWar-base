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
    }
}
