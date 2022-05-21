package pl.edu.pw.elka.prm2t22l.battleships;

public enum Level {
    HARD (10, 10),
    MEDIUM (8, 8),
    EASY (6, 6);

    private final int width;
    private final int height;
    Level(int width, int height) {
        this.width = width;
        this.height = height;
    }
    int getWidth(){
        return width;
    }
    int getHeight(){
        return width;
    }
}
