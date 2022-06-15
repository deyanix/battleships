package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

public abstract class AbstractBoard {
	private final int width;
	private final int height;

	public AbstractBoard(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isLocationAvailable(int x, int y) {
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	}

	public boolean isLocationAvailable(Location location) {
		return isLocationAvailable(location.getX(), location.getY());
	}
}
