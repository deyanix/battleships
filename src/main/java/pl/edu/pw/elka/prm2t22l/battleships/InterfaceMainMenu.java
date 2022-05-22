package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.awt.event.*;

public class InterfaceMainMenu implements ActionListener {

    Frame frameOne;
    Panel panelOne;
    Button bTestOne, bTestTwo, bTestThree;
    Label testLabel;
    TextField textFieldTest;
    GridLayout gridLayoutTest;

    InterfaceMainMenu(){
        testLabel = new Label();
        testLabel.setText("   BATTLESHIPS");
        testLabel.setBackground(Color.GRAY);

        frameOne = new Frame("Main Menu");
        frameOne.setLayout(new FlowLayout());
        panelOne = new Panel();

        bTestOne = new Button("New Game");
        //bTestOne.setBounds(200, 400, 100, 25);
        bTestOne.addActionListener(this);

        bTestTwo = new Button("Load Game");
        //bTestTwo.setBounds(200, 300, 100, 25);
        bTestTwo.addActionListener(this);

        bTestThree = new Button("Quit");
        //bTestThree.setBounds(200, 200, 100, 25);
        bTestThree.addActionListener(this);

        //textFieldTest = new TextField(1);
        //frameOne.add(textFieldTest);

        gridLayoutTest = new GridLayout(4, 1);
        panelOne.setLayout(gridLayoutTest);

        panelOne.add(testLabel);
        panelOne.add(bTestOne);
        panelOne.add(bTestTwo);
        panelOne.add(bTestThree);

        frameOne.add(panelOne);
        frameOne.setSize(400, 500);
        frameOne.setVisible(true);
        frameOne.setBackground(Color.GRAY);
        frameOne.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bTestOne){
        }
    }

}

