package pl.edu.pw.elka.prm2t22l.battleships.entity;

import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;

public class Field {
	private final RasterBoard board;
	private final Location location;
	private FieldState state = FieldState.EMPTY;
	private boolean immutable = false;

	Field(RasterBoard board, Location location) {
		this.board = board;
		this.location = location;
	}

	public Field(RasterBoard board, int x, int y) {
		this(board, new Location(x, y));
	}

	public RasterBoard getBoard() {
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
