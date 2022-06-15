package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;

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
				FieldState currentFieldsState = playerBoard.getField(i, j).getState();
				if (currentFieldsState == FieldState.BATTLESHIP){
					if(computedBoard.getField(i, j).getState() != FieldState.BATTLESHIP){
						wronglyPlaced.add(new Point(i, j));
					}
				}
				else if(currentFieldsState == FieldState.WATER){
					if(computedBoard.getField(i, j).getState() == FieldState.BATTLESHIP){
						wronglyPlaced.add(new Point(i, j));
					}
				}
			}
		}

		return wronglyPlaced;
	}
}
