package pl.edu.pw.elka.prm2t22l.battleships.gui;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceMainMenu extends JPanel {

    public InterfaceMainMenu() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //JFrame fMainMenu = new JFrame("BATTLESHIPS");
        JButton bNewGame = new JButton("New Game");
        JButton bLoadGame = new JButton("Load Game");
        JButton bScoreboard = new JButton("Scoreboard");
        JButton bExit = new JButton("Exit");

        //pMainMenu = new JPanel();
        setBounds(0,0,600,600);
        setLayout(null);

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


        bNewGame.setBounds(225, 0,150,50);
        bLoadGame.setBounds(225, 55,150,50);
        bScoreboard.setBounds(225, 110,150,50);
        bExit.setBounds(225, 165,150,50);
        bNewGame.setFocusable(false);
        bLoadGame.setFocusable(false);
        bScoreboard.setFocusable(false);
        bExit.setFocusable(false);

//------EXIT BUTTON ACTION--------------------------------------
        bExit.addActionListener(e -> System.exit(0));

        pButtonsPlace.add(bNewGame);
        pButtonsPlace.add(bLoadGame);
        pButtonsPlace.add(bScoreboard);
        pButtonsPlace.add(bExit);

        add(pLogoPlace);
        add(pButtonsPlace);

        /*
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\warship.png");
        fMainMenu.setIconImage(icon);
         */


        /*
        fMainMenu.setResizable(false);
        fMainMenu.setSize(600,600);
        fMainMenu.add(pMainMenu);
        fMainMenu.setLayout(null);
        fMainMenu.setVisible(true);

        fMainMenu.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
         */

    }
}
