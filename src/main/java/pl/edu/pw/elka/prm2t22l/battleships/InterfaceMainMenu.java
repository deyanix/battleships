package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class InterfaceMainMenu {

    InterfaceMainMenu() {
        JFrame fMainMenu = new JFrame("BATTLESHIPS");
        JButton bNewGame = new JButton("New Game");
        JButton bLoadGame = new JButton("Load Game");
        JButton bScoreboard = new JButton("Scoreboard");
        JButton bExit = new JButton("Exit");


        bNewGame.setBounds(125, 100,150,50);
        bLoadGame.setBounds(125, 150,150,50);
        bScoreboard.setBounds(125, 200,150,50);
        bExit.setBounds(125, 250,150,50);

//------EXIT BUTTON ACTION--------------------------------------
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        Image icon = Toolkit.getDefaultToolkit().getImage("docs\\warship.png");
        fMainMenu.setIconImage(icon);

        fMainMenu.setSize(400,500);
        fMainMenu.add(bNewGame);
        fMainMenu.add(bLoadGame);
        fMainMenu.add(bScoreboard);
        fMainMenu.add(bExit);
        fMainMenu.setLayout(null);
        fMainMenu.setVisible(true);

        fMainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
