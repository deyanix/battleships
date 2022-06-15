package pl.edu.pw.elka.prm2t22l.battleships.generator;

import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.board.BoardRasterizer;
import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.board.VectorBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Orientation;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Ship;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BoardGenerator {
	private final VectorBoard board;
	private final BoardRasterizer rasterizer;
	private final List<Location> emptyLocations = new LinkedList<>();
	private final Random random;
	private final GameConfiguration configuration;

	public BoardGenerator(GameConfiguration configuration) {
		this.configuration = configuration;
		this.board = new VectorBoard(configuration.getBoardWidth(), configuration.getBoardHeight());
		this.rasterizer = new BoardRasterizer(board);
		this.random = new Random(configuration.getSeed());
		this.initializeGenerator();
	}

	public void initializeGenerator() {
		for (int x = 0; x < configuration.getBoardWidth(); x++) {
			for (int y = 0; y < configuration.getBoardHeight(); y++) {
				emptyLocations.add(new Location(x, y));
			}
		}
	}

	public VectorBoard getVectorBoard() {
		return board;
	}

	public RasterBoard getRasterBoard() {
		return rasterizer.rasterize();
	}

	public Orientation getRandomOrientation() {
		Orientation[] orientations = Orientation.values();
		int index = random.nextInt(orientations.length);
		return orientations[index];
	}

	public int getRandomLocationIndex() {
		return random.nextInt(emptyLocations.size());
	}

	public boolean isEmptyLocation(Location location) {
		return emptyLocations.contains(location);
	}

	public boolean isEmptyLocation(int x, int y) {
		return emptyLocations.contains(new Location(x, y));
	}

	public boolean validateShip(Ship ship) {
		for (Location location : rasterizer.getShipLocations(ship, BoardRasterizer.RASTERIZE_SHIP)) {
			if (!isEmptyLocation(location)) {
				return false;
			}
		}

		for (Location location : rasterizer.getShipLocations(ship, BoardRasterizer.RASTERIZE_BORDER)) {
			if (board.isLocationAvailable(location) && !isEmptyLocation(location)) {
				return false;
			}
		}
		return true;
	}

	public void placeShip(Ship ship) {
		board.addShip(ship);
		for (Location location : rasterizer.getShipLocations(ship, BoardRasterizer.RASTERIZE_BOTH)) {
			emptyLocations.remove(location);
		}
	}

	public Ship insertShip(ShipType type, Location location, Orientation orientation) {
		Ship ship = new Ship(type, location, orientation);
		if (this.validateShip(ship)) {
			this.placeShip(ship);
			return ship;
		}

		ship = new Ship(type, location, orientation.invert());
		if (this.validateShip(ship)) {
			this.placeShip(ship);
			return ship;
		}
		return null;
	}

	public Ship allocateShip(ShipType type) {
		if (emptyLocations.isEmpty()) {
			return null;
		}

		int startLocationIndex = getRandomLocationIndex();
		Orientation orientation = getRandomOrientation();

		int locationIndex = startLocationIndex;
		do {
			Location location = emptyLocations.get(locationIndex);
			Ship ship = insertShip(type, location, orientation);
			if (ship != null) {
				return ship;
			}

			if (++locationIndex >= emptyLocations.size()) {
				locationIndex = 0;
			}
		} while (locationIndex != startLocationIndex);
		return null;
	}

	public boolean generate() {
		for (ShipType type : ShipType.values()) {
			for (int i = 0; i < configuration.getShipAmount(type); i++) {
				if (allocateShip(type) == null) {
					return false;
				}
			}
		}
		return true;
	}
}
