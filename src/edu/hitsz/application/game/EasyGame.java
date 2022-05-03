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
    }
}
