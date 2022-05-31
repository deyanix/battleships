package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.Point;

public enum Ship {
    LONG (3),
    MEDIUM (2),
    SHORT (1);

    private final int length;
    private boolean isVertical;
    private Point placement;
    Ship(int length) {
        this.length = length;
    }
    int getLength(){return length;}
}
