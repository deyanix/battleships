package pl.edu.pw.elka.prm2t22l.battleships.gui;

import java.util.List;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InterfaceMainMenu extends FramePanel{



    public InterfaceMainMenu() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JButton bNewGame = new JButton("New Game");
        JButton bLoadGame = new JButton("Load Game");
        JButton bScoreboard = new JButton("Scoreboard");
        JButton bExit = new JButton("Exit");

        JPanel pLogoPlace = new JPanel();
        pLogoPlace.setBounds(0,0,600,200);

        JPanel pButtonsPlace = new JPanel();
        pButtonsPlace.setBounds(0,200,600,400);
        pButtonsPlace.setLayout(null);

        try {
            BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\battleship_logo.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pLogoPlace.add(lBattleshipLogo);
        } catch (IOException e){}

        bNewGame.setBounds(225, 5,150,50);
        bLoadGame.setBounds(225, 60,150,50);
        bScoreboard.setBounds(225, 115,150,50);
        bExit.setBounds(225, 170,150,50);

        bNewGame.setFocusable(false);
        bLoadGame.setFocusable(false);
        bScoreboard.setFocusable(false);
        bExit.setFocusable(false);

//------NEW GAME BUTTON ACTION----------------------------------
        bNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(2);
            }
        });

//------EXIT BUTTON ACTION--------------------------------------
        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        pButtonsPlace.add(bNewGame);
        pButtonsPlace.add(bLoadGame);
        pButtonsPlace.add(bScoreboard);
        pButtonsPlace.add(bExit);

        setSize(600,600);

        add(pLogoPlace);
        add(pButtonsPlace);
        setLayout(null);
        setVisible(true);
    }


}
