package pl.edu.pw.elka.prm2t22l.battleships;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BoardGenerator {
    private final GameConfiguration chosenConfiguration;
    private Board confBoard;
    private long seed;
    private final int nOfAttempts = 100000;

    public BoardGenerator(GameConfiguration chosenConfiguration) {
        this.chosenConfiguration = chosenConfiguration;
        seed = chosenConfiguration.getSeed();
        makeClearBoard();
    }
    private int getRandomNumber(int min, int max, long seed) {
        Random random = new Random(seed);
        return random.nextInt(max - min) + min;
    }
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
    public Board getConfBoard(){
        return confBoard;
    }
    private void makeClearBoard(){
        confBoard = Board.createEmptyBoard(chosenConfiguration.getLevel().getWidth(),
                                           chosenConfiguration.getLevel().getHeight());
    }

    public boolean placeShips(Ship[] ships){
        boolean succesfullAttempt = true;
        int check= 0;

        backtrackloop:
        for (int i = 0; i < nOfAttempts; i++) {
            check = i;
            makeClearBoard();

            innerloop:
            for (int j = 0; j < ships.length; j++) {
                boolean attempt = placeShip(ships[j]);
                if(!attempt){
                    break innerloop;
                }
                if(attempt && (j+1 == ships.length)){
                    break backtrackloop;
                }
            }
        }

        System.out.println(check);
        if (succesfullAttempt){
            return false;
        }
        return true;
    }
    private boolean placeShip(Ship ship) {
        boolean isVertical = ThreadLocalRandom.current().nextBoolean();
        int maxCol = chosenConfiguration.getLevel().getWidth();
        int maxRow = chosenConfiguration.getLevel().getHeight();

        if (isVertical) {maxCol -= (ship.getLength()-1);}
        else {maxRow -= (ship.getLength()-1);}

        if (maxCol < 1 || maxRow < 1){return false;}

        int startFieldX = getRandomNumber(0, maxRow, seed);
        int startFieldY = getRandomNumber(0, maxCol, seed+1);
        //System.out.println(startFieldX+" "+startFieldY);
        int ShipEndX;
        int ShipEndY;
        if (isVertical) {
            ShipEndX = startFieldX;
            ShipEndY = startFieldY + (ship.getLength()-1);
        }
        else {
            ShipEndX = startFieldX + (ship.getLength()-1);
            ShipEndY = startFieldY;
        }

        int startCheckX = Math.max(0, startFieldX-1);
        int startCheckY = Math.max(0, startFieldY-1);
        int endCheckX = Math.min(ShipEndX+1, chosenConfiguration.getLevel().getWidth()-1);
        int endCheckY = Math.min(ShipEndY+1, chosenConfiguration.getLevel().getHeight()-1);


        boolean canFit = true;
        //System.out.println(startCheckX + " " + startCheckY + " " + endCheckX +" "+ endCheckY);
        outerloop:
        for (int j = startCheckX; j <= endCheckX; j++) {
            for (int k = startCheckY; k <= endCheckY; k++) {
                if (confBoard.getFieldState(j, k) != (FieldState.EMPTY)) {
                    canFit = false;
                    break outerloop;
                }
            }
        }
        if (canFit){
        for (int i = 0; i < ship.getLength(); i++) {
            if (isVertical){
                confBoard.setFieldState(startFieldX, startFieldY+i, FieldState.BATTLESHIP);
            }
            else{
                confBoard.setFieldState(startFieldX+i, startFieldY, FieldState.BATTLESHIP);
            }
        }}
        seed = seed+2;
        return canFit;
    }
}
