package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.BoardComponent;
import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class InterfaceBoardView extends FramePanel {
	private final GamePlayManager manager;
	public InterfaceBoardView(GamePlayManager manager) {
		this.manager = manager;
//------BOARD-PLACE-------------------------------------
		JPanel pBoardPlace = new JPanel();
		pBoardPlace.setBounds(0, 0, 500, 600);

		BoardComponent boardComponent = new BoardComponent(manager);
		boardComponent.setBounds(0, 0, 500, 500);
		pBoardPlace.setLayout(new GridLayout());
		pBoardPlace.add(boardComponent);
		pBoardPlace.setVisible(true);

//------PANEL-Z-PRZYCISKAMI-----------------------------
		JPanel pButtonsPlaceSide = new JPanel();
		pButtonsPlaceSide.setBounds(500, 0, 100, 600);
		pButtonsPlaceSide.setLayout(null);

		JButton bSolution = new JButton("Solution");
		bSolution.addActionListener(e -> {
			if (this.manager.nextHint()) {
				boardComponent.repaint();
			} else {
				JOptionPane.showMessageDialog(this,
						"Currently hints is unavailable",
						"Hint error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton bUndoMove = new JButton("Undo");
		bUndoMove.setEnabled(manager.getConfiguration().isUndoesAvailable());
		bUndoMove.addActionListener(e -> {
			if (this.manager.rollbackTurn()) {
				boardComponent.repaint();
			} else {
				JOptionPane.showMessageDialog(this,
						"Stack of your turns is empty",
						"Undo error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		JButton bSolve = new JButton("Solve");
		bSolve.addActionListener(e -> {
			manager.getBoard().transferToPlayer();
			boardComponent.repaint();
		});

		JButton bCheck = new JButton("Check");
		bCheck.addActionListener(e -> {
			List<Location> differences = this.manager.getBoard().compareBoards();
			if (differences.size() == 0) {
				JOptionPane.showMessageDialog(this,
						"No problems",
						"Congratulation!",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
						"You have " + differences.size() + " problem(s)",
						"Errors",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		JButton bSaveProgress = new JButton("Save");
		bSaveProgress.addActionListener(e -> {
			try {
				manager.save();
				changePanel(1);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		});
		JButton bExitToMm = new JButton("Exit");

		bSolution.setFocusable(false);
		bUndoMove.setFocusable(false);
		bSaveProgress.setFocusable(false);
		bExitToMm.setFocusable(false);

		TimeComponent timeComponent = new TimeComponent(manager);
		timeComponent.setBounds(5, 110, 75, 20);
		pButtonsPlaceSide.add(timeComponent);

		bSolution.setBounds(5, 10, 75, 20);
		bUndoMove.setBounds(5, 40, 75, 20);
		bSolve.setBounds(5, 70, 75, 20);
		bCheck.setBounds(5, 470, 75, 20);
		bSaveProgress.setBounds(5, 500, 75, 20);
		bExitToMm.setBounds(5, 530, 75, 20);

		bExitToMm.addActionListener(e -> changePanel(1));

		JPanel pIconPlace = new JPanel();
		pIconPlace.setSize(100, 100);
		pIconPlace.setBounds(0, 230, 100, 100);

		JLabel lBattleshipLogo = new JLabel(new ImageIcon("src\\main\\resources\\mini_logo.png"));
		pIconPlace.add(lBattleshipLogo);
		pIconPlace.setVisible(true);

		pButtonsPlaceSide.add(pIconPlace);
		pButtonsPlaceSide.add(bExitToMm);
		pButtonsPlaceSide.add(bSaveProgress);
		pButtonsPlaceSide.add(bSolution);
		pButtonsPlaceSide.add(bCheck);
		pButtonsPlaceSide.add(bUndoMove);
		pButtonsPlaceSide.add(bSolve);
		pButtonsPlaceSide.setVisible(true);

		add(pBoardPlace);
		add(pButtonsPlaceSide);

		setLayout(null);
		setSize(600, 600);
		setVisible(true);
	}
}
