package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.parseLong;

public class GameConfiguration {
    public static GameConfiguration getEasyConfiguration(){
        GameConfiguration easyConfiguration = new GameConfiguration();
        easyConfiguration.setBoardWidth(6);
        easyConfiguration.setBoardHeight(6);
        easyConfiguration.setNumberOfStartingHints(8);
        easyConfiguration.setNumberOfHints(4);
        easyConfiguration.setUndoesAvailable(true);

        //Ships
        easyConfiguration.setShipAmount(ShipType.SHORT, 3);
        easyConfiguration.setShipAmount(ShipType.MEDIUM, 2);
        easyConfiguration.setShipAmount(ShipType.LONG, 1);
        return easyConfiguration;
    }
    public static GameConfiguration getMediumConfiguration() {
        GameConfiguration mediumConfiguration = new GameConfiguration();
        mediumConfiguration.setBoardWidth(8);
        mediumConfiguration.setBoardHeight(8);
        mediumConfiguration.setNumberOfStartingHints(6);
        mediumConfiguration.setNumberOfHints(2);
        mediumConfiguration.setUndoesAvailable(true);

        //Ships
        mediumConfiguration.setShipAmount(ShipType.SHORT, 4);
        mediumConfiguration.setShipAmount(ShipType.MEDIUM, 3);
        mediumConfiguration.setShipAmount(ShipType.LONG, 2);
        return mediumConfiguration;
    }
    public static GameConfiguration getHardConfiguration(){
        GameConfiguration mediumConfiguration = new GameConfiguration();
        mediumConfiguration.setBoardWidth(10);
        mediumConfiguration.setBoardHeight(10);
        mediumConfiguration.setNumberOfStartingHints(4);
        mediumConfiguration.setNumberOfHints(1);
        mediumConfiguration.setUndoesAvailable(false);

        //Ships
        mediumConfiguration.setShipAmount(ShipType.SHORT, 5);
        mediumConfiguration.setShipAmount(ShipType.MEDIUM, 3);
        mediumConfiguration.setShipAmount(ShipType.LONG, 2);
        return mediumConfiguration;
    }

    private final Map<ShipType, Integer> shipsAmounts = new HashMap<>();
    private int boardWidth;
    private int boardHeight;
    private int numberOfStartingHints;
    private int numberOfHints;
    private boolean undoesAvailable;
    private long seed;

    public GameConfiguration() { }
    ;

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

    public boolean setSeedText(String seedToBeChecked){
        try{
            setSeed(parseLong(seedToBeChecked));
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
