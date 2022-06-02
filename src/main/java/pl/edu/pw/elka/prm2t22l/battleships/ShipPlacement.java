package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.util.LinkedList;

public class ShipPlacement {
    public ShipPlacement(Board currentBoard) {
        this.boardArr = new int[currentBoard.getWidth()][currentBoard.getHeight()];
    }
    private final int SHIP_PRESENT = 1;
    public LinkedList<Ship> moves = new LinkedList<Ship>();
    private int[][] boardArr;
    public void addMove(Ship newShip){
        moves.add(newShip);
    }
    public void revertMove(){
        moves.removeLast();
        this.boardArr = new int[boardArr.length][boardArr[0].length];
        updateArray();
    }
    public void updateArray(){
        for (Ship currentShip : moves) {
            if (currentShip.getStart() != null){
                int startX = (int) currentShip.getStart().getX();
                int startY = (int) currentShip.getStart().getY();
                if (currentShip.getOrientation()){
                    for (int i = startY; i < startY + currentShip.getLength(); i++) {
                        boardArr[startX][i] = SHIP_PRESENT;
                    }
                }
                else{
                    for (int i = startX; i < startX + currentShip.getLength(); i++) {
                        boardArr[i][startY] = SHIP_PRESENT;
                    }
                }
            }
        }
    }
    public int getFieldState(int x, int y){
        updateArray();
        return boardArr[x][y];
    }
}
