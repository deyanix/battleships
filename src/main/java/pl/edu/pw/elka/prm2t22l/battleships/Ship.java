package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.Point;
import java.util.LinkedList;

public enum Ship {
    LONG (3),
    MEDIUM (2),
    SHORT (1);

    private final int length;
    private boolean isVertical;
    private Point placement;
    Ship(int length) {
        this.length = length;
    }
    public int getLength(){return length;}
    void setStart(int x, int y){
        placement = new Point(x, y);
    }
    Point getStart(){
        return placement;
    }
    void setOrientation(boolean isVertical){
        this.isVertical = isVertical;
    }
    boolean getOrientation(){
        return isVertical;
    }
    void placeShip(Point start){
        this.setOrientation(true);
        this.setStart(start.x, start.y);
    }
    private boolean checkSurrounding(Board currentBoard, ShipPlacement moves, int x, int y){
        int minX = x-1 < 0 ? x : x-1;
        int maxX = x+1 > currentBoard.getWidth() - 1 ? x : x+1;
        int minY = y-1 < 0 ? y : y-1;
        int maxY = y+1 > currentBoard.getHeight() - 1 ? y : y+1;
        //System.out.println(minX + " " + maxX + " " + minY + " " + maxY);
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if(moves.getFieldState(i, j) == 1){
                    return false;
                }
            }
        }
        return true;
    }
    LinkedList placeShip(Board currentBoard, ShipPlacement moves){
        LinkedList<Point> listMoves = new LinkedList<Point>();
        for (int i = 0; i < currentBoard.getWidth(); i++) {
            for (int j = 0; j < currentBoard.getHeight(); j++) {
                if(moves.getFieldState(i, j) != 1){
                    boolean placed = true;
                    for (int k = j; k < this.length; k++) {
                        if(moves.getFieldState(i, k) == 1){
                            placed = false;
                            //if(j+k >= currentBoard.getHeight()) return listMoves;
                            break;
                        }
                    }
                    if (placed){
                        if (checkSurrounding(currentBoard, moves, i,j)) listMoves.add(new Point(i, j));
                    }
                }
            }
        }
        return listMoves;
    }
}
