package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.BoardRasterizer;
import pl.edu.pw.elka.prm2t22l.battleships.board.GameBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Turn;
import pl.edu.pw.elka.prm2t22l.battleships.filemanager.ReadFileManager;
import pl.edu.pw.elka.prm2t22l.battleships.filemanager.SaveFileManager;
import pl.edu.pw.elka.prm2t22l.battleships.generator.BoardGenerator;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class GamePlayManager {
	private static final File SAVE = new File("save.json");
	private static final ReadFileManager readManager = new ReadFileManager();
	public static GamePlayManager loadGame() throws IOException {
		if (!SAVE.isFile()) {
			return null;
		}
		return readManager.readFile(SAVE);
	}

	private static final int N = 10000;
	private final GameConfiguration configuration;
	private final Stack<Turn> turns = new Stack<>();
	private GameBoard board;
	private GameHintManager hintManager;
	private LocalDateTime startTime;
	private Duration passedTime = Duration.ZERO;

	public GamePlayManager(GameConfiguration configuration) {
		this.configuration = configuration;
	}

	public GameConfiguration getConfiguration() {
		return configuration;
	}

	public GameBoard getBoard() {
		return board;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public boolean createBoard() {
		BoardGenerator generator = new BoardGenerator(configuration);
		for (int i = 0; i < N; i++) {
			if (generator.generate()) {
				BoardRasterizer rasterizer = generator.getRasterizer();
				rasterizer.setFillState(FieldState.WATER);
				board = new GameBoard(rasterizer.rasterize());
				hintManager = new GameHintManager(configuration, board);
				showInitialFields();
				return true;
			}
		}
		return false;
	}

	private void showInitialFields() {
		int amount = Math.min(configuration.getNumberOfStartingHints(), board.getCapacity());
		for (int i = 0; i < amount; i++) {
			hintManager.nextHint(false);
		}
	}

	public boolean nextHint() {
		return hintManager.nextHint(true);
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

	public GameHintManager getHintManager() {
		return hintManager;
	}

	public void setPassedTime(Duration passedTime) {
		this.passedTime = passedTime;
	}
	public Duration getPassedTime() {
		return passedTime;
	}

	public void turn(Location location, FieldState state) {
		Field field = board.getPlayerBoard().getField(location);
		FieldState previousState = field.getState();
		field.setState(state);
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
		board.getPlayerBoard().getField(lastTurn.getLocation()).setState(lastTurn.getPreviousState());
		return true;
	}

	public boolean isAvailableRollbackTurn() {
		return turns.empty();
	}

	public void save() throws IOException {
		SaveFileManager saveManager = new SaveFileManager(getBoard().getPlayerBoard(), getConfiguration(), hintManager.getTakenHints(), getCurrentTime().getSeconds());
		saveManager.writeToFile(SAVE);
	}
}
