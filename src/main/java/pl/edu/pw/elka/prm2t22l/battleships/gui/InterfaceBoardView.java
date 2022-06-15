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

	public InterfaceBoardView() {
//------BOARD-PLACE-------------------------------------
		JPanel pBoardPlace = new JPanel();
		pBoardPlace.setBounds(0, 0, 500, 600);

		GameConfiguration configuration = new GameConfiguration();
		configuration.setBoardSize(6, 6);
		configuration.setShipAmount(ShipType.LONG, 1);
		configuration.setShipAmount(ShipType.MEDIUM, 2);
		configuration.setShipAmount(ShipType.SHORT, 1);
		configuration.setNumberOfStartingHints(4);
		configuration.setNumberOfHints(4);
		configuration.setSeed(2);
		configuration.setUndoesAvailable(false);
		manager = new GamePlayManager(configuration);
		manager.createBoard();

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
			if (manager.nextHint()) {
				boardComponent.repaint();
			} else {
				JOptionPane.showMessageDialog(this,
						"Currently hints is unavailable",
						"Hint error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton bUndoMove = new JButton("Undo");
		bUndoMove.setEnabled(configuration.isUndoesAvailable());
		bUndoMove.addActionListener(e -> {
			if (manager.rollbackTurn()) {
				boardComponent.repaint();
			} else {
				JOptionPane.showMessageDialog(this,
						"Stack of your turns is empty",
						"Undo error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		JButton bCheck = new JButton("Check");
		bCheck.addActionListener(e -> {
			List<Location> differenes = manager.getBoard().compareBoards();
		});
		JButton bSaveProgress = new JButton("Save");
		JButton bExitToMm = new JButton("Exit");

		bSolution.setFocusable(false);
		bUndoMove.setFocusable(false);
		bSaveProgress.setFocusable(false);
		bExitToMm.setFocusable(false);

		bSolution.setBounds(5, 10, 75, 20);
		bUndoMove.setBounds(5, 40, 75, 20);
		bCheck.setBounds(5, 470, 75, 20);
		bSaveProgress.setBounds(5, 500, 75, 20);
		bExitToMm.setBounds(5, 530, 75, 20);

		bExitToMm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				changePanel(1);
			}
		});

		JPanel pIconPlace = new JPanel();
		pIconPlace.setSize(100, 100);
		pIconPlace.setBounds(0, 230, 100, 100);

		try {
			BufferedImage iLogo = ImageIO.read(new File("src\\main\\resources\\mini_logo.png"));
			JLabel lBattleshipLogo = new JLabel(new ImageIcon(iLogo));
			pIconPlace.add(lBattleshipLogo);
		} catch (IOException e) {
		}

		pIconPlace.setVisible(true);

		pButtonsPlaceSide.add(pIconPlace);
		pButtonsPlaceSide.add(bExitToMm);
		pButtonsPlaceSide.add(bSaveProgress);
		pButtonsPlaceSide.add(bSolution);
		pButtonsPlaceSide.add(bCheck);
		pButtonsPlaceSide.add(bUndoMove);

		pButtonsPlaceSide.setVisible(true);
//------------------------------------------------------

		add(pBoardPlace);
		add(pButtonsPlaceSide);

		setLayout(null);
		setSize(600, 600);
		setVisible(true);
	}
}
