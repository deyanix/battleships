package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceConfigWindow extends JPanel {

    JLayeredPane lpConfigLayeredPane;
    JPanel pCustomConfig;

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
        pDifficultyConfig.setBounds(0,200,200,240);
        pDifficultyConfig.setLayout(null);

        //pDifficultyConfig.setBackground(Color.BLUE);

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
                pCustomConfig.setVisible(false);
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
                pCustomConfig.setVisible(false);
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
                pCustomConfig.setVisible(false);
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
                //customSettingsEnabler = true;
                //System.out.println(customSettingsEnabler);
                JComponent source = (JComponent) e.getSource();
                //set the  popup label location at center of the source component
                pCustomConfig.setLocation(new Point(200,200));
                pCustomConfig.setVisible(true);
            }
        });

        JTextField tfSeedPlace = new JTextField("Seed Place:", 20);
        tfSeedPlace.setBounds(30,170,150,20);

        pDifficultyConfig.add(lSelectDiffLvl);
        pDifficultyConfig.add(rbDifficultyEasy);
        pDifficultyConfig.add(rbDifficultyNormal);
        pDifficultyConfig.add(rbDifficultyHard);
        pDifficultyConfig.add(rbCustomMode);
        pDifficultyConfig.add(tfSeedPlace);

        ButtonGroup group = new ButtonGroup();
        group.add(rbDifficultyEasy);
        group.add(rbDifficultyNormal);
        group.add(rbDifficultyHard);
        group.add(rbCustomMode);

        pDifficultyConfig.setVisible(true);

//------PANEL KASTOMIZACJI POZIOMU TRUDNOŚCI----------------------
        pCustomConfig = new JPanel();
        pCustomConfig.setBounds(200,200,400,400);
        pCustomConfig.setLayout(null);
        //pCustomConfig.setBackground(Color.green);

        JLabel lWidthX = new JLabel("Width of the Board:");
        JLabel lHightY = new JLabel("Hight of the Board:");
        JLabel lVisibleFieldsNum = new JLabel("Visible Filds:");
        JLabel lSkipsNum = new JLabel("Possible Skips:");
        JLabel lWithdrawalsNum = new JLabel("Possible Withdrawals:");
        JLabel lNumberOfShips = new JLabel("Number of Ships:");

        //SZEROKOŚĆ PLANSZY (OX)
        JSlider sWidthX = new JSlider(JSlider.HORIZONTAL,2,10,6);
        sWidthX.setMinorTickSpacing(1);
        sWidthX.setMajorTickSpacing(2);
        sWidthX.setPaintTicks(true);
        sWidthX.setPaintLabels(true);
        sWidthX.setFocusable(false);
        sWidthX.setBounds(41,15,130,40);
        lWidthX.setBounds(43,0,128,15);
        pCustomConfig.add(sWidthX);
        //WYSOKOŚĆ PLANSZY (OY)
        JSlider sHightY = new JSlider(JSlider.HORIZONTAL,2,10,6);
        sHightY.setMinorTickSpacing(1);
        sHightY.setMajorTickSpacing(2);
        sHightY.setPaintTicks(true);
        sHightY.setPaintLabels(true);
        sHightY.setFocusable(false);
        sHightY.setBounds(191,15,130,40);
        lHightY.setBounds(193,0,128,15);
        pCustomConfig.add(sHightY);
        //LICZBA WIDOCZNYCH NA POCZĄTKU PÓL
        JSlider sVisibleFieldsNum = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sVisibleFieldsNum.setMinorTickSpacing(1);
        sVisibleFieldsNum.setMajorTickSpacing(2);
        sVisibleFieldsNum.setPaintTicks(true);
        sVisibleFieldsNum.setPaintLabels(true);
        sVisibleFieldsNum.setFocusable(false);
        sVisibleFieldsNum.setBounds(20,105,100,40);
        lVisibleFieldsNum.setBounds(22,90,98,15);
        pCustomConfig.add(sVisibleFieldsNum);
        //LICZBA DOZWOLONYCH POMINIEC
        JSlider sSkipsNum = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sSkipsNum.setMinorTickSpacing(1);
        sSkipsNum.setMajorTickSpacing(2);
        sSkipsNum.setPaintTicks(true);
        sSkipsNum.setPaintLabels(true);
        sSkipsNum.setFocusable(false);
        sSkipsNum.setBounds(130,105,100,40);
        lSkipsNum.setBounds(132,90,98,15);
        pCustomConfig.add(sSkipsNum);
        //LICZBA DOZWOLONYCH COFNIEC
        JSlider sWithdrawalsNum = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sWithdrawalsNum.setMinorTickSpacing(1);
        sWithdrawalsNum.setMajorTickSpacing(2);
        sWithdrawalsNum.setPaintTicks(true);
        sWithdrawalsNum.setPaintLabels(true);
        sWithdrawalsNum.setFocusable(false);
        sWithdrawalsNum.setBounds(240,105,100,40);
        lWithdrawalsNum.setBounds(242,90,120,15);
        pCustomConfig.add(sWithdrawalsNum);

        //LICZBA STATKÓW I
        JSlider sSmallShips = new JSlider(JSlider.HORIZONTAL,0,10,4);
        sSmallShips.setMinorTickSpacing(1);
        sSmallShips.setMajorTickSpacing(2);
        sSmallShips.setPaintTicks(true);
        sSmallShips.setPaintLabels(true);
        sSmallShips.setFocusable(false);
        sSmallShips.setBounds(20,300,100,40);
        lNumberOfShips.setBounds(20,150,100,40);
        pCustomConfig.add(sSmallShips);
        //LICZBA STATKÓW II
        JSlider sMediumShips = new JSlider(JSlider.HORIZONTAL,0,10,3);
        sMediumShips.setMinorTickSpacing(1);
        sMediumShips.setMajorTickSpacing(2);
        sMediumShips.setPaintTicks(true);
        sMediumShips.setPaintLabels(true);
        sMediumShips.setFocusable(false);
        sMediumShips.setBounds(130,300,100,40);
        pCustomConfig.add(sMediumShips);
        //LICZBA STATKÓW III
        JSlider sLargeShips = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sLargeShips.setMinorTickSpacing(1);
        sLargeShips.setMajorTickSpacing(2);
        sLargeShips.setPaintTicks(true);
        sLargeShips.setPaintLabels(true);
        sLargeShips.setFocusable(false);
        sLargeShips.setBounds(240,300,100,40);
        pCustomConfig.add(sLargeShips);

        try {
            BufferedImage iLogo1 = ImageIO.read(new File("src\\main\\resources\\Ship3.png"));
            JLabel lShip1 = new JLabel(new ImageIcon(iLogo1));
            lShip1.setBounds(45,260,50,40);
            pCustomConfig.add(lShip1);
        } catch (IOException e){}
        try {
            BufferedImage iLogo2 = ImageIO.read(new File("src\\main\\resources\\Ship3.png"));
            JLabel lShip2 = new JLabel(new ImageIcon(iLogo2));
            lShip2.setBounds(155,215,50,125);
            pCustomConfig.add(lShip2);
        } catch (IOException e){}
        try {
            BufferedImage iLogo3 = ImageIO.read(new File("src\\main\\resources\\Ship3.png"));
            JLabel lShip3 = new JLabel(new ImageIcon(iLogo3));
            lShip3.setBounds(265,170,50,130);
            pCustomConfig.add(lShip3);
        } catch (IOException e){}

        pCustomConfig.add(lWidthX);
        pCustomConfig.add(lHightY);
        pCustomConfig.add(lVisibleFieldsNum);
        pCustomConfig.add(lSkipsNum);
        pCustomConfig.add(lWithdrawalsNum);
        pCustomConfig.add(lNumberOfShips);



        pCustomConfig.setVisible(false);  //wcześniej w tej linijce bylo: pCustomConfig.setVisible(customSettingsEnabler);
//------PANEL Z PRZYCISKAMI---------------------------------------
        JPanel pButtonsPlace = new JPanel();
        pButtonsPlace.setBounds(0,440,200,160);
        pButtonsPlace.setLayout(null);

        //pButtonsPlace.setBackground(Color.cyan);

        JButton bSave = new JButton("Save");
        bSave.setFocusable(false);
        bSave.setBounds(30,0,67,40);

        JButton bBack = new JButton("Back");
        bBack.setFocusable(false);
        bBack.setBounds(103,0,67,40);

        JButton bPlay = new JButton("Play");
        bPlay.setFocusable(false);
        bPlay.setBounds(30,45,140,40);

        pButtonsPlace.add(bSave);
        pButtonsPlace.add(bBack);
        pButtonsPlace.add(bPlay);

        pButtonsPlace.setVisible(true);
//------PUSTY PANEL ("ZAKRYWAJĄCY CONFIG")------------------------
        JPanel pBlankPanel = new JPanel();
        pBlankPanel.setBounds(200,200,400,400);
        pBlankPanel.setBackground(Color.cyan);
        pBlankPanel.setVisible(false);
//------LAYERED PANE----------------------------------------------
        lpConfigLayeredPane = new JLayeredPane();
        lpConfigLayeredPane.add(pBlankPanel, JLayeredPane.DEFAULT_LAYER);
        lpConfigLayeredPane.add(pCustomConfig, JLayeredPane.POPUP_LAYER);

//----------------------------------------------------------------
        setSize(600,600);
        setLayout(null);
        add(pLogoPlace);
        add(pDifficultyConfig);
        add(pCustomConfig);
        add(pButtonsPlace);

        setVisible(true);
    }
}
