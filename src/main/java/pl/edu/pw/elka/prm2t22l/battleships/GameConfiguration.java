package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.HashMap;
import java.util.Map;

public class GameConfiguration {
    private final Map<ShipType, Integer> shipsAmounts = new HashMap<>();
    private int boardWidth;
    private int boardHeight;
    private int numberOfVisibleFields;
    private int numberOfAvailableUndoes;
    private boolean hintsAvailable;
    private Level level;
    private long seed;

    public void setShipAmount(ShipType type, int amount) {
        shipsAmounts.put(type, amount);
    }

    public int getShipAmount(ShipType type) {
        return shipsAmounts.getOrDefault(type, 0);
    }

    public void setBoardSize(int width, int height) {
        setBoardWidth(width);
        setBoardHeight(height);
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getNumberOfVisibleFields() {
        return numberOfVisibleFields;
    }

    public void setNumberOfVisibleFields(int numberOfVisibleFields) {
        this.numberOfVisibleFields = numberOfVisibleFields;
    }

    public int getNumberOfAvailableUndoes() {
        return numberOfAvailableUndoes;
    }

    public void setNumberOfAvailableUndoes(int numberOfAvailableUndoes) {
        this.numberOfAvailableUndoes = numberOfAvailableUndoes;
    }

    public boolean isHintsAvailable() {
        return hintsAvailable;
    }

    public void setHintsAvailable(boolean hintsAvailable) {
        this.hintsAvailable = hintsAvailable;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
