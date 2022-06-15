package pl.edu.pw.elka.prm2t22l.battleships.board;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

	public List<Point> checkPlayerBoard(){
		List<Point> wronglyPlaced = new ArrayList<>();

		for (int i = 0; i < computedBoard.getHeight(); i++) {
			for (int j = 0; j < computedBoard.getWidth(); j++) {
				if (computedBoard.getField(i, j) != playerBoard.getField(i, j)){
					wronglyPlaced.add(new Point(i, j));
				}
			}
		}

		return wronglyPlaced;
	}
}
