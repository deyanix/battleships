package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;

import java.util.Random;

public class BoardGenerator {
    private final GameConfiguration chosenConfiguration;
    private Board configuration;
    private final int nOfAttempts = 10000;
    private final Random randomGen;
    private ShipPlacement moves;

    public BoardGenerator(GameConfiguration chosenConfiguration) {
        this.chosenConfiguration = chosenConfiguration;
        this.randomGen = new Random(chosenConfiguration.getSeed());
        makeClearBoard();
    }
    private int getRandomNumber(int min, int max) {
        return randomGen.nextInt(max - min) + min;
    }
    public Board getConfiguration(){
        return configuration;
    }
    private void makeClearBoard(){
        configuration = new Board(chosenConfiguration.getLevel().getWidth(),
                                           chosenConfiguration.getLevel().getHeight());
    }
    public boolean smartPlaceShips(Ship[] ships){
        ShipPlacement moves = new ShipPlacement(configuration);
        Ship[] sortedShips = Arrays.stream(ships).sorted(Comparator.comparing(Ship::getLength).reversed())
                .toArray(Ship[]::new);

        int startOfCriticalPath = 1;
        for (int i = 0; i < sortedShips.length; i++) {
            System.out.println("Placing " + (i+1) + " ship currently placed ships" + moves.moves);
            System.out.println(sortedShips[i].placeShip(configuration, moves));
            if (sortedShips[i].placeShip(configuration, moves).size() == 0){
                for (int j = 0; j < sortedShips.length - startOfCriticalPath; j++) {
                    moves.revertMove();
                }
                System.out.println("revert");
                i = startOfCriticalPath - 1;
            }
            else{
                if (sortedShips[i].placeShip(configuration, moves).size() == 1){
                    startOfCriticalPath = i - 2;
                }
                int length = sortedShips[i].placeShip(configuration, moves).size();
                Point start = (Point) sortedShips[i].placeShip(configuration, moves).get(getRandomNumber(0, length));
                System.out.println(start);
                sortedShips[i].placeShip(start);
                moves.addMove(sortedShips[i]);
            }
        }
        this.moves = moves;
        return false;
    }
    /*public boolean placeShips(Ship[] ships){
        Ship[] sortedShips = Arrays.stream(ships).sorted(Comparator.comparing(Ship::getLength).reversed())
                                                 .toArray(Ship[]::new);
        boolean succesfullAttempt = true;
        int check= 0;

        backtrackloop:
        for (int i = 0; i < nOfAttempts; i++) {
            check = i;
            makeClearBoard();
            innerloop:
            for (int j = 0; j < sortedShips.length; j++) {
                boolean attempt = placeShip(sortedShips[j]);
                if(!attempt){
                    break innerloop;
                }
                if(attempt && (j+1 == sortedShips.length)){
                    break backtrackloop;
                }
            }
        }

        System.out.println(check);
        if (succesfullAttempt){
            return false;
        }
        return true;
    }*/
    private boolean placeShip(Ship ship, ShipPlacement moves) {
        boolean isVertical = randomGen.nextBoolean();
        int maxCol = chosenConfiguration.getLevel().getWidth();
        int maxRow = chosenConfiguration.getLevel().getHeight();

        if (isVertical) {maxCol -= (ship.getLength()-1);}
        else {maxRow -= (ship.getLength()-1);}

        if (maxCol < 1 || maxRow < 1){return false;}

        int startFieldX = getRandomNumber(0, maxRow);
        int startFieldY = getRandomNumber(0, maxCol);
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
                if (moves.getFieldState(j, k) != 0) {
                    canFit = false;
                    break outerloop;
                }
            }
        }
        if (canFit){
            ship.setOrientation(isVertical);
            ship.setStart(startFieldX, startFieldY);
            for (int i = 0; i < ship.getLength(); i++) {
                if (isVertical){
                    //configuration.getField(startFieldX, startFieldY+i).setState( FieldState.BATTLESHIP);
                }
                else{
                    //configuration.getField(startFieldX+i, startFieldY).setState( FieldState.BATTLESHIP);
                }
            }
        }
        this.moves = moves;
        return canFit;
    }
    public void render(){
        for (int i = 0; i < configuration.getHeight(); i++) {
            for (int j = 0; j < configuration.getWidth(); j++) {
                boolean isShip = (moves.getFieldState(i, j) == 1);
                if (isShip) configuration.getField(i, j).setState(FieldState.BATTLESHIP);
            }
        }
    }

}
