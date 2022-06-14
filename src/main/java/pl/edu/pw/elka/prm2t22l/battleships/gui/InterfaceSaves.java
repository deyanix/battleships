package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceSaves extends FramePanel{

    public InterfaceSaves() {


        JLabel lSaves = new JLabel("SAVES");

        lSaves.setBounds(30,0,200,100);
        lSaves.setFocusable(false);

        JButton bBack = new JButton("Back");
        bBack.setFocusable(false);
        bBack.setBounds(420,480,120,50);
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(1);
            }
        });

        add(lSaves);
        add(bBack);

        setLayout(null);
        setSize(600,600);
        setVisible(true);
    }

}
