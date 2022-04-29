package edu.hitsz.application;

import swing.Ranking;
import swing.Startup;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * 程序入口
 *
 * @author hitsz
 */
public class Main {

    public static final Object OBJECT = new Object();
    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static boolean soundEffect;
    public static GameMode gameMode;
    /**
     * 枚举定义游戏难度模式
     */
    public enum GameMode{
        //简单、普通、困难3种模式
        EASY,NORMAL,HARD
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Hello Aircraft War");

        // 获得屏幕的分辨率，初始化 Frame
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Aircraft War");
        JPanel mainPanel;
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //启动界面
        mainPanel = new Startup().getMainPanel();
        frame.setContentPane(mainPanel);
        frame.setVisible(true);
        synchronized (OBJECT){
            try{
                OBJECT.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(mainPanel);
        //游戏界面
        Game game = new Game();
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();
        synchronized (OBJECT){
            try{
                OBJECT.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        frame.remove(game);
        //排行榜界面
        Ranking ranking = new Ranking();
        mainPanel = ranking.getMainPanel();
        frame.setContentPane(mainPanel);
        frame.setVisible(true);

    }
}
