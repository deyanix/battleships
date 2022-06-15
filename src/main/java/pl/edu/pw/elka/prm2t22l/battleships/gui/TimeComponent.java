package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeComponent extends JLabel {
    private GamePlayManager manager;
    public TimeComponent(GamePlayManager manager) {
        this.manager = manager;
        updateTime();
        Timer simpleTimer = new Timer(1000, e -> updateTime());
        simpleTimer.setRepeats(true);
        simpleTimer.start();
    }

    private void updateTime() {
        long s = manager.getCurrentTime().getSeconds();
        String text = String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
        this.setText(text);
    }
}
