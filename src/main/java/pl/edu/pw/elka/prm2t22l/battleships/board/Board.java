package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.util.Iterator;
import java.util.stream.IntStream;

public class Board implements Iterable<Field> {
	private final int width;
	private final int height;
	private final Field[][] fields;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.fields = generateFields(width, height);
	}

	private Field[][] generateFields(int width, int height) {
		return IntStream.range(0, height)
				.mapToObj(y -> IntStream.range(0, width)
					.mapToObj(x -> new Field(this, x, y))
						.toArray(Field[]::new))
				.toArray(Field[][]::new);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Field[][] getFields() {
		return fields;
	}

	public boolean hasField(int x, int y) {
		return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
	}

	public Field getField(int x, int y) {
		if (hasField(x, y)) {
			return fields[y][x];
		}
		return null;
	}
	public Field getField(Location location) {
		return getField(location.getX(), location.getY());
	}

	public void display() {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				System.out.print(getField(x, y).getState().name().charAt(0));
			}
			System.out.println();
		}
	}

	@Override
	public Iterator<Field> iterator() {
		return new BoardIterator(this);
	}
}
