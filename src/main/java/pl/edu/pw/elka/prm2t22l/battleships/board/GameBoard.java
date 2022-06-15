package pl.edu.pw.elka.prm2t22l.battleships.board;

public class GameBoard extends AbstractBoard {
	private final RasterBoard computedBoard;
	private final RasterBoard playerBoard;

	public GameBoard(RasterBoard board) {
		super(board.getWidth(), board.getHeight());
		this.computedBoard = board;
		this.playerBoard = new RasterBoard(board.getWidth(), board.getHeight());
	}

	public RasterBoard getComputedBoard() {
		return computedBoard;
	}

	public RasterBoard getPlayerBoard() {
		return playerBoard;
	}
}
