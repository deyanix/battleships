package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;
import pl.edu.pw.elka.prm2t22l.battleships.gui.event.ChangePanelEvent;
import pl.edu.pw.elka.prm2t22l.battleships.gui.event.PlayEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameInterface extends JFrame implements ActionListener {
    private GamePlayManager gamePlayManager;
    public FrameInterface() {

        Image icon = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\warship.png");
        setIconImage(icon);
        setTitle("BATTLESHIPS");
        setResizable(false);
        setSize(600,600);
        changePanel(1);

        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getID() == ChangePanelEvent.EVENT_ID) {
            ChangePanelEvent changePanelEvent = (ChangePanelEvent) e;
            changePanel(changePanelEvent.getTarget());
        }
        if (e.getID() == PlayEvent.EVENT_ID) {
            PlayEvent playEvent = (PlayEvent) e;
            gamePlayManager = playEvent.getManager();
            changePanel(3);
        }
    }

    private void changePanel(int target) {
        try {
            switch (target) {
                case 1: setFramePanel(new InterfaceMainMenu()); break;
                case 2: setFramePanel(new InterfaceConfigWindow()); break;
                case 3: setFramePanel(new InterfaceBoardView(gamePlayManager)); break;
                case 4: setFramePanel(new InterfaceSaves()); break;
                case 5: setFramePanel(new InterfaceScoreboard()); break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void setFramePanel(FramePanel panel) {
        panel.addActionListener(this);
        setContentPane(panel);
    }
}
