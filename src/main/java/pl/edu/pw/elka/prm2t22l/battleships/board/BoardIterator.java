package pl.edu.pw.elka.prm2t22l.battleships.board;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.util.Iterator;

public class BoardIterator implements Iterator<Field> {
	private final Board board;
	private int index = 0;

	public BoardIterator(Board board) {
		this.board = board;
	}

	private Field getLocation(int index) {
		return board.getField(index % board.getHeight(), index / board.getHeight());
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
