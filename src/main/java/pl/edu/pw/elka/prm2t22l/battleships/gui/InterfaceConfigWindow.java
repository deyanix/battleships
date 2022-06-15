package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;
import pl.edu.pw.elka.prm2t22l.battleships.gui.event.PlayEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import static java.lang.Long.parseLong;

public class InterfaceConfigWindow extends FramePanel {

    private final JSlider sWidthX;
    private final JSlider sHightY;
    private final JSlider sVisibleFieldsNum;
    private final JSlider sSkipsNum;
    private final JCheckBox cbPossibleUndos;
    private final JSlider sSmallShips;
    private final JSlider sMediumShips;
    private final JSlider sLargeShips;
    private final JTextField tfSeedPlace;
    private final JRadioButton rbDifficultyEasy;
    private final JRadioButton rbDifficultyNormal;
    private final JRadioButton rbDifficultyHard;
    private final JRadioButton rbCustomMode;


    private JPanel pCustomConfig;

    public InterfaceConfigWindow() {

//------MIEJSCE NA LOGO-------------------------------------------
        JPanel pLogoPlace = new JPanel();
        pLogoPlace.setBounds(0,0,600,200);

        JLabel lBattleshipLogo = new JLabel(new ImageIcon("src\\main\\resources\\battleship_logo.png"));
        pLogoPlace.add(lBattleshipLogo);

//------PANEL POZIOMY TRUDNOŚCI-----------------------------------
        JPanel pDifficultyConfig = new JPanel();
        pDifficultyConfig.setBounds(0,200,200,240);
        pDifficultyConfig.setLayout(null);

        JLabel lSelectDiffLvl = new JLabel("Select Difficulty Level:");
        lSelectDiffLvl.setBounds(20,0,150,25);




        rbDifficultyEasy = new JRadioButton(" Easy ");
        rbDifficultyEasy.setBounds(25,25,150,25);
        rbDifficultyEasy.setFocusable(false);
        rbDifficultyEasy.addItemListener(e -> {
            pCustomConfig.setVisible(false);
        });

        rbDifficultyNormal = new JRadioButton(" Normal ");
        rbDifficultyNormal.setBounds(25,50,150,25);
        rbDifficultyNormal.setFocusable(false);
        rbDifficultyNormal.setSelected(true);
        rbDifficultyNormal.addItemListener(e -> {
            pCustomConfig.setVisible(false);
        });

        rbDifficultyHard = new JRadioButton(" Hard ");
        rbDifficultyHard.setBounds(25,75,150,25);
        rbDifficultyHard.setFocusable(false);
        rbDifficultyHard.addItemListener(e -> {
            pCustomConfig.setVisible(false);
        });

        rbCustomMode = new JRadioButton(" Custom Mode ");
        rbCustomMode.setBounds(20,110,150,25);
        rbCustomMode.setFocusable(false);
        rbCustomMode.addItemListener(e -> {
            JComponent source = (JComponent) e.getSource();
            pCustomConfig.setLocation(new Point(200,200));
            pCustomConfig.setVisible(true);
        });


        tfSeedPlace = new JTextField("Seed Place:", 20);
        tfSeedPlace.setForeground(Color.GRAY);
        tfSeedPlace.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfSeedPlace.getText().equals("Seed Place:")) {
                    tfSeedPlace.setText("");
                    tfSeedPlace.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfSeedPlace.getText().isEmpty()) {
                    tfSeedPlace.setForeground(Color.GRAY);
                    tfSeedPlace.setText("Seed Place:");
                }

            }
        });
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

        JLabel lWidthX = new JLabel("Width of the Board:");
        JLabel lHightY = new JLabel("Hight of the Board:");
        JLabel lVisibleFieldsNum = new JLabel("Hints");
        JLabel lSkipsNum = new JLabel("Starting Hints:");
        JLabel lWithdrawalsNum = new JLabel("Possible Undos:");
        JLabel lNumberOfShips = new JLabel("Number of Ships:");

        //SZEROKOŚĆ PLANSZY (OX)
        sWidthX = new JSlider(JSlider.HORIZONTAL,4,10,6);
        sWidthX.setMinorTickSpacing(1);
        sWidthX.setMajorTickSpacing(2);
        sWidthX.setPaintTicks(true);
        sWidthX.setPaintLabels(true);
        sWidthX.setFocusable(false);
        sWidthX.setBounds(41,15,130,40);
        lWidthX.setBounds(43,0,128,15);
        pCustomConfig.add(sWidthX);
        //WYSOKOŚĆ PLANSZY (OY)
        sHightY = new JSlider(JSlider.HORIZONTAL,4,10,6);
        sHightY.setMinorTickSpacing(1);
        sHightY.setMajorTickSpacing(2);
        sHightY.setPaintTicks(true);
        sHightY.setPaintLabels(true);
        sHightY.setFocusable(false);
        sHightY.setBounds(191,15,130,40);
        lHightY.setBounds(193,0,128,15);
        pCustomConfig.add(sHightY);
        //LICZBA PODPOWIEDZI
        sVisibleFieldsNum = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sVisibleFieldsNum.setMinorTickSpacing(1);
        sVisibleFieldsNum.setMajorTickSpacing(2);
        sVisibleFieldsNum.setPaintTicks(true);
        sVisibleFieldsNum.setPaintLabels(true);
        sVisibleFieldsNum.setFocusable(false);
        sVisibleFieldsNum.setBounds(20,105,100,40);
        lVisibleFieldsNum.setBounds(22,90,98,15);
        pCustomConfig.add(sVisibleFieldsNum);
        //LICZBA PODPOWIEDZI STARTOWYCH
        sSkipsNum = new JSlider(JSlider.HORIZONTAL,1,10,2);
        sSkipsNum.setMinorTickSpacing(1);
        sSkipsNum.setMajorTickSpacing(3);
        sSkipsNum.setPaintTicks(true);
        sSkipsNum.setPaintLabels(true);
        sSkipsNum.setFocusable(false);
        sSkipsNum.setBounds(130,105,100,40);
        lSkipsNum.setBounds(132,90,98,15);
        pCustomConfig.add(sSkipsNum);


        cbPossibleUndos = new JCheckBox("Possible Undos");
        cbPossibleUndos.setFocusable(false);
        cbPossibleUndos.setBounds(245,100,100,40);
        pCustomConfig.add(cbPossibleUndos);

        //LICZBA STATKÓW I
        sSmallShips = new JSlider(JSlider.HORIZONTAL,0,10,4);
        sSmallShips.setMinorTickSpacing(1);
        sSmallShips.setMajorTickSpacing(2);
        sSmallShips.setPaintTicks(true);
        sSmallShips.setPaintLabels(true);
        sSmallShips.setFocusable(false);
        sSmallShips.setBounds(20,300,100,40);
        lNumberOfShips.setBounds(20,150,100,40);
        pCustomConfig.add(sSmallShips);
        //LICZBA STATKÓW II
        sMediumShips = new JSlider(JSlider.HORIZONTAL,0,10,3);
        sMediumShips.setMinorTickSpacing(1);
        sMediumShips.setMajorTickSpacing(2);
        sMediumShips.setPaintTicks(true);
        sMediumShips.setPaintLabels(true);
        sMediumShips.setFocusable(false);
        sMediumShips.setBounds(130,300,100,40);
        pCustomConfig.add(sMediumShips);
        //LICZBA STATKÓW III
        sLargeShips = new JSlider(JSlider.HORIZONTAL,0,10,2);
        sLargeShips.setMinorTickSpacing(1);
        sLargeShips.setMajorTickSpacing(2);
        sLargeShips.setPaintTicks(true);
        sLargeShips.setPaintLabels(true);
        sLargeShips.setFocusable(false);
        sLargeShips.setBounds(240,300,100,40);
        pCustomConfig.add(sLargeShips);

        JLabel lShip1 = new JLabel(new ImageIcon("src\\main\\resources\\Ship3.png"));
        lShip1.setBounds(45,260,50,40);
        pCustomConfig.add(lShip1);
        JLabel lShip2 = new JLabel(new ImageIcon("src\\main\\resources\\Ship3.png"));
        lShip2.setBounds(155,215,50,125);
        pCustomConfig.add(lShip2);
        JLabel lShip3 = new JLabel(new ImageIcon("src\\main\\resources\\Ship3.png"));
        lShip3.setBounds(265,170,50,130);
        pCustomConfig.add(lShip3);

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

        JButton bSave = new JButton("Save");
        bSave.setFocusable(false);
        bSave.setBounds(30,0,67,40);
        bSave.addActionListener(e -> {
            GamePlayManager manager = createGameManager();
            if (manager != null) {
                try {
                    manager.saveBoard();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton bBack = new JButton("Back");
        bBack.setFocusable(false);
        bBack.setBounds(103,0,67,40);
        bBack.addActionListener(e -> changePanel(1));

        JButton bPlay = new JButton("Play");
        bPlay.setFocusable(false);
        bPlay.setBounds(30,45,140,40);
        bPlay.addActionListener(e -> {
            GamePlayManager manager = createGameManager();
            if (manager != null) {
                invokeEvent(new PlayEvent(this, manager));
            }
        });

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
        JLayeredPane lpConfigLayeredPane = new JLayeredPane();
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

    private GamePlayManager createGameManager() {
        GameConfiguration configuration = new GameConfiguration();
        if (rbCustomMode.isSelected()){
            configuration.setBoardSize(sWidthX.getValue(), sHightY.getValue());
            configuration.setNumberOfStartingHints(sVisibleFieldsNum.getValue());
            configuration.setNumberOfHints(sSkipsNum.getValue());
            configuration.setUndoesAvailable(cbPossibleUndos.isSelected());
            configuration.setShipAmount(ShipType.SHORT, sSmallShips.getValue());
            configuration.setShipAmount(ShipType.MEDIUM, sMediumShips.getValue());
            configuration.setShipAmount(ShipType.LONG, sLargeShips.getValue());
        } else {
            if (rbDifficultyEasy.isSelected()) {
                configuration = GameConfiguration.getEasyConfiguration();
            } else if(rbDifficultyNormal.isSelected()) {
                configuration = GameConfiguration.getMediumConfiguration();
            } else {
                configuration = GameConfiguration.getHardConfiguration();
            }
        }

        if (configuration.setSeedText(tfSeedPlace.getText())){
            GamePlayManager manager = new GamePlayManager(configuration);
            if (!manager.createBoard()) {
                JOptionPane.showMessageDialog(
                        null,
                        "This configuration is too hard for generator",
                        "Board generating error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                return manager;
            }
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "The seed you've just entered can't be transformed to required data type LONG",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }
}
