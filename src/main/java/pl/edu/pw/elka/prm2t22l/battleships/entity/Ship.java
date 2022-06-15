package pl.edu.pw.elka.prm2t22l.battleships.entity;

import java.util.Objects;

public class Ship {
	private final ShipType type;
	private final Location location;
	private final Orientation orientation;

	public Ship(ShipType type, Location location, Orientation orientation) {
		this.type = type;
		this.location = location;
		this.orientation = orientation;
	}

	public ShipType getType() {
		return type;
	}

	public Location getLocation() {
		return location;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ship ship = (Ship) o;
		return type == ship.type && Objects.equals(location, ship.location) && orientation == ship.orientation;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, location, orientation);
	}

	@Override
	public String toString() {
		return "Ship{" +
				"type=" + type +
				", location=" + location +
				", orientation=" + orientation +
				'}';
	}
}
