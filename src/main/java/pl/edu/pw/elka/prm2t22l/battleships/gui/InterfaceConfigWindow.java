package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceConfigWindow extends JPanel {

    public int diggicultyLevel = 2;         //będzie zapisywać poziom trudności (od 0 do 4, gdzie 0 to custom)
    public boolean focusability = true; //zmienia możliwość zaznaczenia RadioButtonów
    public boolean customSettingsEnabler = false;

    public InterfaceConfigWindow() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

//------MIEJSCE NA LOGO-------------------------------------------
        JPanel pLogoPlace = new JPanel();
        pLogoPlace.setBounds(0,0,600,200);

        try {
            BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\battleship_logo.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pLogoPlace.add(lBattleshipLogo);
        } catch (IOException e){}

//------PANEL POZIOMY TRUDNOŚCI-----------------------------------
        JPanel pDifficultyConfig = new JPanel();
        pDifficultyConfig.setBounds(0,200,200,200);
        pDifficultyConfig.setLayout(null);

        JLabel lSelectDiffLvl = new JLabel("Select Difficulty Level:");
        lSelectDiffLvl.setBounds(20,0,150,25);

        JRadioButton rbDifficultyEasy = new JRadioButton(" Easy ");
        rbDifficultyEasy.setBounds(25,25,150,25);
        rbDifficultyEasy.setFocusable(false);
        rbDifficultyEasy.setEnabled(focusability);
        rbDifficultyEasy.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                diggicultyLevel = 1;
            }
        });

        JRadioButton rbDifficultyNormal = new JRadioButton(" Normal ");
        rbDifficultyNormal.setBounds(25,50,150,25);
        rbDifficultyNormal.setFocusable(false);
        rbDifficultyNormal.setEnabled(focusability);
        rbDifficultyNormal.setSelected(true);
        rbDifficultyNormal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                diggicultyLevel = 2;
            }
        });

        JRadioButton rbDifficultyHard = new JRadioButton(" Hard ");
        rbDifficultyHard.setBounds(25,75,150,25);
        rbDifficultyHard.setFocusable(false);
        rbDifficultyHard.setEnabled(focusability);
        rbDifficultyHard.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                diggicultyLevel = 3;
            }
        });

        JRadioButton rbCustomMode = new JRadioButton(" Custom Mode ");
        rbCustomMode.setBounds(20,110,150,25);
        rbCustomMode.setFocusable(false);
        rbCustomMode.setEnabled(focusability);
        rbCustomMode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                diggicultyLevel = 0;
                customSettingsEnabler = true;
            }
        });

        pDifficultyConfig.add(lSelectDiffLvl);
        pDifficultyConfig.add(rbDifficultyEasy);
        pDifficultyConfig.add(rbDifficultyNormal);
        pDifficultyConfig.add(rbDifficultyHard);
        pDifficultyConfig.add(rbCustomMode);

        ButtonGroup group = new ButtonGroup();
        group.add(rbDifficultyEasy);
        group.add(rbDifficultyNormal);
        group.add(rbDifficultyHard);
        group.add(rbCustomMode);

        pDifficultyConfig.setVisible(true);

//------PANEL KASTOMIZACJI POZIOMU TRUDNOŚCI----------------------
        JPanel pCustomConfig = new JPanel();
        pCustomConfig.setBounds(200,200,400,400);
        pCustomConfig.setLayout(null);

        JLabel lTest = new JLabel("testets");
        lTest.setBounds(0,0,20,20);

        pCustomConfig.add(lTest);

        pCustomConfig.setVisible(customSettingsEnabler);
//------PANEL Z PRZYCISKAMI---------------------------------------
        JPanel pButtonsPlace = new JPanel();




        setSize(600,600);
        setLayout(null);
        add(pLogoPlace);
        add(pDifficultyConfig);

        setVisible(true);
    }
}
