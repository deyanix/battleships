package pl.edu.pw.elka.prm2t22l.battleships;

import java.util.concurrent.ThreadLocalRandom;

public class BoardGenerator {
    private final GameConfiguration chosenConfiguration;
    private Board confBoard;
    private final int nOfAttempts = 100000;

    public BoardGenerator(GameConfiguration chosenConfiguration) {
        this.chosenConfiguration = chosenConfiguration;
        makeClearBoard();
    }
    private void makeClearBoard(){
        confBoard = Board.createEmptyBoard(chosenConfiguration.getLevel().getWidth(),
                                           chosenConfiguration.getLevel().getHeight());
    }

    private void placeShips(){
        for (int i = 0; i < nOfAttempts; i++) {
            makeClearBoard();
        }
    }
    private void placeShip() {
        boolean isVertical = ThreadLocalRandom.current().nextBoolean();
        if (isVertical) {

        }
    }
}
