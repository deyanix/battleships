package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.GameBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.util.Arrays;
import java.util.Random;

public class GameHintManager {
	private final GameConfiguration configuration;
	private final GameBoard board;
	private final Random random;
	private int takenHints = 0;

	public GameHintManager(GameConfiguration configuration, GameBoard board) {
		this.configuration = configuration;
		this.board = board;
		this.random = new Random(configuration.getSeed());
	}

	public GameConfiguration getConfiguration() {
		return configuration;
	}

	public GameBoard getBoard() {
		return board;
	}

	private Location getRandomLocation() {
		return new Location(
				random.nextInt(getBoard().getWidth()),
				random.nextInt(getBoard().getHeight()));
	}

	public boolean hasMutableFields() {
		return Arrays.stream(board.getPlayerBoard().getFields())
				.anyMatch(row -> Arrays.stream(row).anyMatch(field -> !field.isImmutable()));
	}

	public int getAvailableHint() {
		return configuration.getNumberOfHints() - takenHints;
	}

	public boolean hasHint() {
		System.out.println(getAvailableHint() + ":" + (getAvailableHint() > 0));
		return getAvailableHint() > 0;
	}

	public boolean nextHint(boolean inGame) {
		if (inGame) {
			if (!hasHint()) {
				return false;
			}
			takenHints++;
		}
		if (!hasMutableFields()) {
			return false;
		}

		Field computedField;
		Field playerField;
		do {
			Location currentLocation = getRandomLocation();
			computedField = getBoard().getComputedBoard().getField(currentLocation);
			playerField = getBoard().getPlayerBoard().getField(currentLocation);
		} while (playerField.isImmutable());
		playerField.setState(computedField.getState());
		playerField.setImmutable(true);
		return true;
	}
}
