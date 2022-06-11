package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.gui.InterfaceMainMenu;

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
		board.display();

		new Thread() {
			@Override
			public void run() {
				try {
					GamePlayManager manager = new GamePlayManager(board);
					manager.start();
					Thread.sleep(2000);
					System.out.println(manager.getPassedTime());
					System.out.println(manager.getCurrentTime());
					manager.pause();
					manager.start();
					Thread.sleep(2000);
					System.out.println(manager.getPassedTime());
					System.out.println(manager.getCurrentTime());
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}.start();

		boardGenTest(); // BOARD GENERATION TEST

		new InterfaceMainMenu();  //INTERFACE TEST
	}

	public static void boardGenTest(){

		for (int i = 0; i < 1; i++) {
			GameConfiguration newConf = new GameConfiguration(3, 3,
					true, Level.VMIN);

			BoardGenerator newBoard = new BoardGenerator(newConf);
			newBoard.smartPlaceShips(new Ship[]{Ship.SHORT, Ship.SHORT,Ship.SHORT, Ship.SHORT});
			newBoard.render();
			Board generatedBoard = newBoard.getConfiguration();
			System.out.println("------ ------");
			generatedBoard.display();
		}
	};
}
