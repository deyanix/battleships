package pl.edu.pw.elka.prm2t22l.battleships.gui;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceMainMenu extends Canvas{

    public InterfaceMainMenu() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame fMainMenu = new JFrame("BATTLESHIPS");
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
            BufferedImage iLogo = ImageIO.read(new File("docs\\battleship_logo.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pLogoPlace.add(lBattleshipLogo);
        } catch (IOException e){}

        bNewGame.setBounds(225, 5,150,50);
        bLoadGame.setBounds(225, 60,150,50);
        bScoreboard.setBounds(225, 115,150,50);
        bExit.setBounds(225, 170,150,50);

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

        Image icon = Toolkit.getDefaultToolkit().getImage("docs\\warship.png");
        fMainMenu.setIconImage(icon);

        fMainMenu.setSize(600,600);
        /*
        fMainMenu.add(bNewGame);
        fMainMenu.add(bLoadGame);
        fMainMenu.add(bScoreboard);
        fMainMenu.add(bExit);
         */
        fMainMenu.add(pLogoPlace);
        fMainMenu.add(pButtonsPlace);
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
