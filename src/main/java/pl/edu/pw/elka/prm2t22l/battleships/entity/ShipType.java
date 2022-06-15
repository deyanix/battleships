package pl.edu.pw.elka.prm2t22l.battleships.entity;

public enum ShipType {
    LONG (3),
    MEDIUM (2),
    SHORT (1);

    private final int length;
    ShipType(int length) {
        this.length = length;
    }
    public int getLength() {
        return length;
    }
}
