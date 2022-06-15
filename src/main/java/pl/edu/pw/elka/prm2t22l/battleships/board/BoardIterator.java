package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.util.Iterator;

public class BoardIterator implements Iterator<Field> {
	private final RasterBoard board;
	private int index = 0;

	public BoardIterator(RasterBoard board) {
		this.board = board;
	}

	private Field getLocation(int index) {
		return board.getField(index % board.getWidth(), index / board.getWidth());
	}

	@Override
	public boolean hasNext() {
		return index < board.getWidth() * board.getHeight();
	}

	@Override
	public Field next() {
		return getLocation(index++);
	}
}
