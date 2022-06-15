package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InterfaceBoardView extends FramePanel {

    JPanel pButtonsPlaceSide;
    JPanel pBoardPlace;

    public InterfaceBoardView() {
//------BOARD-PLACE-------------------------------------
        pBoardPlace = new JPanel();
        pBoardPlace.setBounds(0,0,500,600);
        //pBoardPlace.setBackground(Color.cyan);

        pBoardPlace.setVisible(true);
//------PANEL-Z-PRZYCISKAMI-----------------------------
        pButtonsPlaceSide = new JPanel();
        pButtonsPlaceSide.setBounds(500,0,100,600);
        pButtonsPlaceSide.setLayout(null);

        JButton bSolution = new JButton("Solution");
        JButton bShowError = new JButton("Error");
        JButton bUndoMove = new JButton("Undo");
        JButton bSaveProgress = new JButton("Save");
        JButton bExitToMm = new JButton("Exit");

        bSolution.setFocusable(false);
        bShowError.setFocusable(false);
        bUndoMove.setFocusable(false);
        bSaveProgress.setFocusable(false);
        bExitToMm.setFocusable(false);

        bSolution.setBounds(5,10,75,20);
        bShowError.setBounds(5,40,75,20);
        bUndoMove.setBounds(5,70,75,20);
        bSaveProgress.setBounds(5,500,75,20);
        bExitToMm.setBounds(5,530,75,20);

        bExitToMm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(1);
            }
        });

        JPanel pIconPlace = new JPanel();
        pIconPlace.setSize(100,100);
        pIconPlace.setBounds(0,230,100,100);

        try {
            BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\mini_logo.png"));
            JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
            pIconPlace.add(lBattleshipLogo);
        } catch (IOException e){}

        pIconPlace.setVisible(true);

        pButtonsPlaceSide.add(pIconPlace);
        pButtonsPlaceSide.add(bExitToMm);
        pButtonsPlaceSide.add(bSaveProgress);
        pButtonsPlaceSide.add(bShowError);
        pButtonsPlaceSide.add(bSolution);
        pButtonsPlaceSide.add(bUndoMove);

        pButtonsPlaceSide.setVisible(true);
//------------------------------------------------------

        add(pBoardPlace);
        add(pButtonsPlaceSide);

        setLayout(null);
        setSize(600,600);
        setVisible(true);


    }
}
