package pl.edu.pw.elka.prm2t22l.battleships;

import java.util.Random;

public class GameConfiguration {

    private final int numberOfVisibleFields;
    private final int numberOfAvailableUndos;
    private final boolean hintsAvailable;
    private final Level level;
    private long seed;

    public GameConfiguration(int numberOfVisibleFields, int numberOfAvailableUndos, boolean hintsAvailable, long seed, Level level){
        this.numberOfVisibleFields = numberOfVisibleFields;
        this.numberOfAvailableUndos = numberOfAvailableUndos;
        this.hintsAvailable = hintsAvailable;
        this.level = level;
        this.seed = seed;
    }
    public GameConfiguration(int numberOfVisibleFields, int numberOfAvailableUndos, boolean hintsAvailable, Level level){
        this.numberOfVisibleFields = numberOfVisibleFields;
        this.numberOfAvailableUndos = numberOfAvailableUndos;
        this.hintsAvailable = hintsAvailable;
        this.level = level;
        Random random = new Random();
        this.seed = random.nextLong();
    }
    public void prnLevelName(){
        for (Level currentLevel : Level.values()) {
            System.out.println(currentLevel);
        }
    }
    public int getNumberOfVisibleFields() {
        return numberOfVisibleFields;
    }

    public int getNumberOfAvailableUndoes() {
        return numberOfAvailableUndos;
    }

    public boolean isHintsAvailable() {
        return hintsAvailable;
    }

    public Level getLevel() {
        return level;
    }

    public long getSeed() {
        return seed;
    }
}
