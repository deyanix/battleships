package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeComponent extends JLabel {
    private GamePlayManager currentGamePlayManager;

    public TimeComponent(GamePlayManager currentGamePlayManager) {
        super();
        this.currentGamePlayManager = currentGamePlayManager;
        super.setText(currentGamePlayManager.getCurrentTime().toString());
        SimpleTimer.start();
    }
    ActionListener updateTime = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel clockLabel = (JLabel) e.getSource();
            clockLabel.setText(currentGamePlayManager.getCurrentTime().toString());
        }
    };
    Timer SimpleTimer = new Timer(1000, updateTime);
}
