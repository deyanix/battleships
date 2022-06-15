package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Long.parseLong;

public class GameConfiguration {
    public static GameConfiguration getEasyConfiguration(){
        return new GameConfiguration(6, 6, 10, 10, true);
    }
    public static GameConfiguration getMediumConfiguration() {
        return new GameConfiguration(8, 8, 8, 4, true);
    }
    public static GameConfiguration getHardConfiguration(){
        return new GameConfiguration(10, 10, 4, 2, false);
    }

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

    public int getNumberOfAvailableHints() {
        return numberOfHints;
    }

    public void setNumberOfAvailableHints(int numberOfAvailableHints) {
        this.numberOfHints = numberOfAvailableHints;
    }

    public boolean areUndoesAvailable() {
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

    public boolean checkSeed(String seedToBeChecked){
        try{
            setSeed(parseLong(seedToBeChecked));
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    public void printOutConfiguration(){
        System.out.println(getBoardHeight() + ":" + getBoardWidth());
    }
    public GameConfiguration(){};
    public GameConfiguration(int boardWidth, int boardHeight, int numberOfStartingHints, int numberOfHints, boolean undoesAvailable) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.numberOfStartingHints = numberOfStartingHints;
        this.numberOfHints = numberOfHints;
        this.undoesAvailable = undoesAvailable;
        this.seed = ThreadLocalRandom.current().nextLong();
    }
}
