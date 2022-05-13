package pl.edu.pw.elka.prm2t22l.battleships;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {
    public static Board createEmptyBoard(int width, int height) {
        FieldState[] fields = new FieldState[width*height];
        Arrays.fill(fields, FieldState.EMPTY);
        return new Board(width, height, fields);
    }

    private final int width;
    private final int height;
    private final FieldState[] fields;

    public Board(int width, int height, FieldState[] fields) {
        this.width = width;
        this.height = height;
        this.fields = fields;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public FieldState[] getFieldStates() {
        return fields;
    }

    public int getFieldsInRowByState(FieldState state, int row) {
        return (int) IntStream.range(0, height)
                .map(x -> getIndex(x, row))
                .mapToObj(this::getFieldState)
                .filter(state::equals)
                .count();
    }

    public int getFieldsInColumnByState(FieldState state, int column) {
        return (int) IntStream.range(0, height)
                .map(y -> getIndex(column, y))
                .mapToObj(this::getFieldState)
                .filter(state::equals)
                .count();
    }

    public FieldState getFieldState(int index) {
        return fields[index];
    }

    public FieldState getFieldState(int x, int y) {
        return getFieldState(getIndex(x, y));
    }

    public void setFieldState(int index, FieldState state) {
        fields[index] = state;
    }

    public void setFieldState(Location location, FieldState state) {
        setFieldState(getIndex(location), state);
    }

    public void setFieldState(int x, int y, FieldState state) {
        setFieldState(getIndex(x, y), state);
    }

    public FieldState getFieldState(Location location) {
        return getFieldState(getIndex(location));
    }

    public int getIndex(int x, int y) {
        return y * width + x;
    }

    public int getIndex(Location location) {
        return getIndex(location.getX(), location.getY());
    }

    public Location getLocation(int index) {
        return new Location(index % width, index / width);
    }

    public void display() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                System.out.print(getFieldState(x, y).name().charAt(0));
            }
            System.out.println();
        }
    }
}
