package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Direction;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

public class Field {
	private final Board board;
	private final Location location;
	private FieldState state = FieldState.EMPTY;
	private boolean immutable = false;

	Field(Board board, Location location) {
		this.board = board;
		this.location = location;
	}

	public Field(Board board, int x, int y) {
		this(board, new Location(x, y));
	}

	public Board getBoard() {
		return board;
	}

	public Location getLocation() {
		return location;
	}

	public FieldState getState() {
		return state;
	}

	public void setState(FieldState state) {
		this.state = state;
	}

	public boolean isImmutable() {
		return immutable;
	}

	public void setImmutable(boolean immutable) {
		this.immutable = immutable;
	}
}
