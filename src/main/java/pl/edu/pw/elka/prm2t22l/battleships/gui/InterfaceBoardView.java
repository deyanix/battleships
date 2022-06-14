package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.swing.*;
import java.awt.*;

public class InterfaceBoardView extends FramePanel {

    public InterfaceBoardView() {

        JPanel pTestTest = new JPanel();
        pTestTest.setBackground(Color.cyan);
        pTestTest.setBounds(0,0,600,600);
        pTestTest.setVisible(true);

        add(pTestTest);
        setLayout(null);
        setSize(600,600);
        setVisible(true);


    }
}
