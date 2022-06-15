package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.GameBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameHintManager {
	private final GameConfiguration configuration;
	private final GameBoard board;
	private final Random random;

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

	public void nextHint() {
		Field computedField;
		Field playerField;
		do {
			Location currentLocation = getRandomLocation();
			computedField = getBoard().getComputedBoard().getField(currentLocation);
			playerField = getBoard().getPlayerBoard().getField(currentLocation);
		} while (playerField.isImmutable());
		playerField.setState(computedField.getState());
		playerField.setImmutable(true);
	}
}
