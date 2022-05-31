package pl.edu.pw.elka.prm2t22l.battleships;

public class Main {
	public static void main(String[] args) throws Exception {
		Board board = Board.createEmptyBoard(3, 3);
		board.setFieldState(0, 0, FieldState.EMPTY);
		board.setFieldState(1, 0, FieldState.WATER);
		board.setFieldState(2, 0, FieldState.BATTLESHIP);
		board.setFieldState(0, 1, FieldState.EMPTY);
		board.setFieldState(1, 1, FieldState.WATER);
		board.setFieldState(2, 1, FieldState.BATTLESHIP);
		board.setFieldState(0, 2, FieldState.BATTLESHIP);
		board.setFieldState(1, 2, FieldState.EMPTY);
		board.setFieldState(2, 2, FieldState.BATTLESHIP);


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

		for (int i = 0; i < 10; i++) {
			GameConfiguration newConf = new GameConfiguration(3, 3,
					true, Level.EASY);

			BoardGenerator newBoard = new BoardGenerator(newConf);
			newBoard.placeShips(new Ship[]{Ship.SHORT, Ship.SHORT,Ship.SHORT, Ship.MEDIUM, Ship.LONG, Ship.MEDIUM});

			Board generatedBoard = newBoard.getConfiguration();
			generatedBoard.display();
		}
	};
}
