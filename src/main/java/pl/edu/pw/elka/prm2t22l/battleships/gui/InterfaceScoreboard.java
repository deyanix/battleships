package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceScoreboard extends FramePanel{

    public InterfaceScoreboard() {
        JPanel pIconPlace = new JPanel();
        pIconPlace.setSize(600,600);
        pIconPlace.setBounds(0,0,600,600);

        try {
            BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\important.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pIconPlace.add(lBattleshipLogo);
        } catch (IOException e){}

        pIconPlace.setVisible(true);

        add(pIconPlace);
        setLayout(null);
        setSize(600,600);
        setVisible(true);
    }

}
