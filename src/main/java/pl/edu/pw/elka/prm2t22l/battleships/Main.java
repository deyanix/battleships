package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.gui.InterfaceMainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.Flow;

public class Main {
	public static void main(String[] args) throws Exception {
		Board board = new Board(3, 3);
		board.getField(0, 0).setState(FieldState.EMPTY);
		board.getField(1, 0).setState(FieldState.WATER);
		board.getField(2, 0).setState(FieldState.BATTLESHIP);
		board.getField(0, 1).setState(FieldState.EMPTY);
		board.getField(1, 1).setState(FieldState.WATER);
		board.getField(2, 1).setState(FieldState.BATTLESHIP);
		board.getField(0, 2).setState(FieldState.BATTLESHIP);
		board.getField(1, 2).setState(FieldState.EMPTY);
		board.getField(2, 2).setState(FieldState.BATTLESHIP);


		JFrame frame = new JFrame();
		frame.setSize(600,600);
		frame.setContentPane(new BoardComponent(board));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void boardGenTest(){

		for (int i = 0; i < 10; i++) {
			GameConfiguration newConf = new GameConfiguration(3, 3,
					true, Level.EASY);

			BoardGenerator newBoard = new BoardGenerator(newConf);
			newBoard.placeShips(new Ship[]{Ship.SHORT, Ship.SHORT,Ship.SHORT, Ship.MEDIUM, Ship.MEDIUM, Ship.LONG});

			Board generatedBoard = newBoard.getConfBoard();
			generatedBoard.display();
		}
	};
}
