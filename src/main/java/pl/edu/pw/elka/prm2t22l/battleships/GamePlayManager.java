package pl.edu.pw.elka.prm2t22l.battleships;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Stack;

public class GamePlayManager {
	private final Board board;
	private final Stack<Turn> turns = new Stack<>();
	private LocalDateTime startTime;
	private Duration passedTime = Duration.ZERO;

	public GamePlayManager(Board board) {
		this.board = board;
	}

	public Board getBoard() {
		return board;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void start() {
		startTime = LocalDateTime.now();
	}

	public void pause() {
		passedTime = getCurrentTime();
		startTime = null;
	}

	public Duration getCurrentTime() {
		if (startTime == null) {
			return passedTime;
		}
		return Duration.between(startTime, LocalDateTime.now()).plus(passedTime);
	}

	public Duration getPassedTime() {
		return passedTime;
	}

	public void turn(Location location, FieldState state) {
		FieldState previousState = board.getFieldState(location);
		board.setFieldState(location, state);
		turns.push(new Turn(location, previousState, state));
	}

	public void turn(int x, int y, FieldState state) {
		turn(new Location(x, y), state);
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
