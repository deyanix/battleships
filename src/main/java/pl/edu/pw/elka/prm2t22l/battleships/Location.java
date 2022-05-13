package pl.edu.pw.elka.prm2t22l.battleships;

public class Location {
    private final int x;
    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location translate(int dx, int dy) {
        return new Location(x + dx, y + dy);
    }
}
