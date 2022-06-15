package pl.edu.pw.elka.prm2t22l.battleships.entity;

public enum Orientation {
	VERTICAL,
	HORIZONTAL;

	public Orientation invert() {
		return switch (this) {
			case HORIZONTAL -> VERTICAL;
			case VERTICAL -> HORIZONTAL;
		};
	}
}
