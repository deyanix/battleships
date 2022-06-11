package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

public class Turn {
	private final Location location;
	private final FieldState previousState;
	private final FieldState state;

	public Turn(Location location, FieldState previousState, FieldState state) {
		this.location = location;
		this.previousState = previousState;
		this.state = state;
	}

	public Location getLocation() {
		return location;
	}

	public FieldState getPreviousState() {
		return previousState;
	}

	public FieldState getState() {
		return state;
	}
}
