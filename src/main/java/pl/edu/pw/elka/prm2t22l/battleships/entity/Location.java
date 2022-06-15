package pl.edu.pw.elka.prm2t22l.battleships.entity;

import java.util.Objects;

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

    public Location translate(Orientation orientation, int along, int across) {
        return switch (orientation) {
            case VERTICAL -> translate(along, across);
            case HORIZONTAL -> translate(across, along);
        };
    }


    public Location translate(Orientation orientation, int along) {
        return translate(orientation, along, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
