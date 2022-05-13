package pl.edu.pw.elka.prm2t22l.battleships;

import java.util.Stack;

public class GamePlayManager {
	private Board board;
	private final Stack<Turn> turns = new Stack<>();

	public void turn(Location location, FieldState state) {
		FieldState previousState = board.getFieldState(location);
		board.setFieldState(location, state);
		turns.push(new Turn(location, previousState, state));
	}

	public boolean rollbackTurn() {
		if (isAvailableRollbackTurn()) {
			return false;
		}

		Turn lastTurn = turns.pop();
		board.setFieldState(lastTurn.getLocation(), lastTurn.getPreviousState());
		return true;
	}

	public boolean isAvailableRollbackTurn() {
		return turns.empty();
	}
}
