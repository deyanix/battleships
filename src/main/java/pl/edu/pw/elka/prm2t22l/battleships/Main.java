package pl.edu.pw.elka.prm2t22l.battleships;

public class Main {
	public static void main(String[] args) {
		Board board = Board.createEmptyBoard(3, 3);
		// EWB
		// EWB
		// BEB
		board.display();
		board.setFieldState(0, 0, FieldState.EMPTY);
		board.setFieldState(1, 0, FieldState.WATER);
		board.setFieldState(2, 0, FieldState.BATTLESHIP);

		board.setFieldState(0, 1, FieldState.EMPTY);
		board.setFieldState(1, 1, FieldState.WATER);
		board.setFieldState(2, 1, FieldState.BATTLESHIP);

		board.setFieldState(0, 2, FieldState.BATTLESHIP);
		board.setFieldState(1, 2, FieldState.EMPTY);
		board.setFieldState(2, 2, FieldState.BATTLESHIP);

		for (int i = 0; i < board.getWidth(); i++) {
			System.out.printf("Column %d: %d%n", i, board.getFieldsInColumnByState(FieldState.BATTLESHIP, i));
		}

		for (int i = 0; i < board.getHeight(); i++) {
			System.out.printf("Row %d: %d%n", i, board.getFieldsInRowByState(FieldState.BATTLESHIP, i));
		}

		//boardGenTest();
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
