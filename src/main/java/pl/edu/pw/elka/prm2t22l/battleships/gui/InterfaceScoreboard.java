package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceScoreboard extends FramePanel{

    public InterfaceScoreboard() {
        JPanel pIconPlace = new JPanel();
        pIconPlace.setSize(600,600);
        pIconPlace.setBounds(0,0,600,400);

        try {
            BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\important.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pIconPlace.add(lBattleshipLogo);
        } catch (IOException e){}

        pIconPlace.setVisible(true);

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
        add(bBack);
        setLayout(null);
        setSize(600,600);
        setVisible(true);
    }

}
