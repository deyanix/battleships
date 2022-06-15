package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

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

	public void transferToPlayer() {
		for (Field field : computedBoard) {
			Field playerField = playerBoard.getField(field.getLocation());
			playerField.setState(field.getState());
			playerField.setImmutable(true);
		}
	}

	public List<Location> compareBoards() {
		List<Location> differences = new ArrayList<>();
		for (int i = 0; i < computedBoard.getHeight(); i++) {
			for (int j = 0; j < computedBoard.getWidth(); j++) {
				FieldState currentState = playerBoard.getField(i, j).getState();
				FieldState computedState = computedBoard.getField(i, j).getState();
				if (currentState != computedState) {
					differences.add(new Location(i, j));
				}
			}
		}
		return differences;
	}
}
