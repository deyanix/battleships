package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceSaves extends FramePanel{

    public InterfaceSaves() {
//------MIEJSCE NA NAPIS---------------------------------------------
        JPanel pIconPlace = new JPanel();

        JLabel lBattleshipLogo = new JLabel(new ImageIcon("src\\main\\resources\\saves.png"));
        pIconPlace.add(lBattleshipLogo);

        pIconPlace.setBounds(205,10,200,70);
//------MIEJSCE NA PRZYCISKI Z SAVEAMI--------------------------------
        JPanel pSavesPlace = new JPanel();
        pSavesPlace.setBounds(200,90,100,1000);
        GridLayout gl = new GridLayout(20,1);
        pSavesPlace.setLayout(gl);

        pSavesPlace.add(new JButton("Save1"));
        pSavesPlace.add(new JButton("Save2"));
        pSavesPlace.add(new JButton("Save3"));
        pSavesPlace.add(new JButton("Save4"));
        pSavesPlace.add(new JButton("Save5"));
        pSavesPlace.add(new JButton("Save6"));
        pSavesPlace.add(new JButton("Save7"));
        pSavesPlace.add(new JButton("Save8"));
        pSavesPlace.add(new JButton("Save9"));
        pSavesPlace.add(new JButton("Save10"));
        pSavesPlace.add(new JButton("Save11"));
        pSavesPlace.add(new JButton("Save12"));
        pSavesPlace.add(new JButton("Save13"));
        pSavesPlace.add(new JButton("Save14"));
        pSavesPlace.add(new JButton("Save15"));
        pSavesPlace.add(new JButton("Save16"));
        pSavesPlace.add(new JButton("Save17"));
        pSavesPlace.add(new JButton("Save18"));
        pSavesPlace.add(new JButton("Save19"));
        pSavesPlace.add(new JButton("Save20"));

        pSavesPlace.setVisible(true);

        JScrollPane scScrollablePanel = new JScrollPane(pSavesPlace);
        scScrollablePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scScrollablePanel.setBounds(200,100,205,400);
        scScrollablePanel.setVisible(true);
//------MIEJSCE NA PRZYSICK BACK--------------------------------------
        JButton bBack = new JButton("Back");
        bBack.setFocusable(false);
        bBack.setBounds(435,480,120,50);
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(1);
            }
        });

        add(pIconPlace);
        add(scScrollablePanel);
        add(bBack);

        setLayout(null);
        setSize(600,600);
        setVisible(true);
    }

}
