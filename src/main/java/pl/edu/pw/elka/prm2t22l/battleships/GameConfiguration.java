package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.HashMap;
import java.util.Map;

public class GameConfiguration {
    private final Map<ShipType, Integer> shipsAmounts = new HashMap<>();
    private int boardWidth;
    private int boardHeight;
    private int numberOfStartingHints;
    private int numberOfHints;
    private boolean undoesAvailable;
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

    public int getNumberOfStartingHints() {
        return numberOfStartingHints;
    }

    public void setNumberOfStartingHints(int numberOfStartingHints) {
        this.numberOfStartingHints = numberOfStartingHints;
    }

    public int getNumberOfHints() {
        return numberOfHints;
    }

    public void setNumberOfHints(int numberOfHints) {
        this.numberOfHints = numberOfHints;
    }

    public boolean isUndoesAvailable() {
        return undoesAvailable;
    }

    public void setUndoesAvailable(boolean undoesAvailable) {
        this.undoesAvailable = undoesAvailable;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}
