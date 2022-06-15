package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Orientation;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Ship;

import java.util.ArrayList;
import java.util.List;

public class BoardRasterizer {
	public static final int RASTERIZE_SHIP = 0b01;
	public static final int RASTERIZE_BORDER = 0b10;
	public static final int RASTERIZE_BOTH = RASTERIZE_SHIP | RASTERIZE_BORDER;
	private final VectorBoard board;

	public BoardRasterizer(VectorBoard board) {
		this.board = board;
	}

	public Location[] getShipLocations(Ship ship, int rasterizeFlag) {
		List<Location> locations = new ArrayList<>();
		Orientation orientation = ship.getOrientation();
		int length = ship.getType().getLength();
		for (int i = 0; i < length; i++) {
			Location location = ship.getLocation().translate(orientation, i);
			if ((rasterizeFlag & RASTERIZE_SHIP) != 0) {
				locations.add(location);
			}
			if ((rasterizeFlag & RASTERIZE_BORDER) != 0) {
				if (i == 0) {
					locations.add(location.translate(orientation, -1, -1));
					locations.add(location.translate(orientation, -1, 1));
				}
				if (i == length-1) {
					locations.add(location.translate(orientation, 1, -1));
					locations.add(location.translate(orientation, 1, 1));
				}
				locations.add(location.translate(orientation.invert(), -1));
				locations.add(location.translate(orientation.invert(), 1));
			}
		}
		return locations.toArray(new Location[0]);
	}

}
