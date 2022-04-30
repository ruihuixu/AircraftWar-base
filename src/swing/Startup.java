package swing;

import edu.hitsz.application.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 一徐
 */
public class Startup {
    private JPanel mainPanel;
    private JButton easyButton;
    private JButton hardButton;
    private JButton normalButton;
    private JComboBox switchComboBox;
    private JLabel musicLabel;

    public Startup() {
        easyButton.addActionListener(actionEvent -> {
            setSoundEffect();
            Main.gameMode = Main.GameMode.EASY;
            synchronized (Main.OBJECT){
                Main.OBJECT.notify();
            }
        });
        normalButton.addActionListener(actionEvent -> {
            setSoundEffect();
            Main.gameMode = Main.GameMode.NORMAL;
            synchronized (Main.OBJECT){
                Main.OBJECT.notify();
            }
        });
        hardButton.addActionListener(actionEvent -> {
            setSoundEffect();
            Main.gameMode = Main.GameMode.HARD;
            synchronized (Main.OBJECT){
                Main.OBJECT.notify();
            }
        });
    }
    private void setSoundEffect(){
        String[] items = {"开","关"};
        String strItem = switchComboBox.getSelectedItem().toString();
        if(strItem.equals(items[0])){
            Main.soundEffect = true;
        }else{
            Main.soundEffect = false;
        }
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
}
