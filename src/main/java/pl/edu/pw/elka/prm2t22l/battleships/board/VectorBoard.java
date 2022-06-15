package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Ship;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.ArrayList;
import java.util.List;

public class VectorBoard extends AbstractBoard {
	private final List<Ship> ships = new ArrayList<>();

	public VectorBoard(int width, int height) {
		super(width, height);
	}

	public int getShipAmount(ShipType type) {
		return (int) ships.stream()
				.map(Ship::getType)
				.filter(type::equals)
				.count();
	}

	public void addShip(Ship ship) {
		ships.add(ship);
	}

	public void removeShip(Ship ship) {
		ships.remove(ship);
	}

	public Ship[] getShips() {
		return ships.toArray(new Ship[0]);
	}
}
