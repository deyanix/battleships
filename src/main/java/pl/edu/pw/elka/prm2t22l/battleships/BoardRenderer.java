package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.Field;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

public class BoardRenderer {
    private final Board board;
    private final Dimension size;
    private final int fieldSize;

    public BoardRenderer(Board board, Dimension size) {
        this.board = board;
        this.size = size;
        this.fieldSize = calculateFieldSize();
    }

    public BoardRenderer(Board board, int width, int height) {
        this(board, new Dimension(width, height));
    }

    public BoardRenderer(Board board) {
        this(board, new Dimension((board.getWidth()+1) * 50, (board.getHeight()+1) * 50));
    }

    protected void saveImage(File file) throws IOException {
        BufferedImage bufferedImage = new BufferedImage((int) size.getWidth(), (int) size.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.createGraphics();
        renderBoard(g);
        ImageIO.write(bufferedImage, "png", file);
    }


    public void renderBoard(Graphics g) {
        drawLabels(g);
        drawField(g);
    }

    private void drawField(Graphics g) {
        for (Field field : board) {
            Point point = mapToImagePoint(field.getLocation());
            if (field.getState() == FieldState.WATER) {
                g.setColor(Color.BLUE);
                g.fillRect((int) point.getX(), (int) point.getY(), fieldSize, fieldSize);
            } else if(field.getState() == FieldState.BATTLESHIP) {
                g.setColor(Color.BLACK);
                g.fillRect((int) point.getX(), (int) point.getY(), fieldSize, fieldSize);
            }

            g.setColor(Color.BLACK);
            g.drawRect((int) point.getX(), (int) point.getY(), fieldSize, fieldSize);
        }
    }

    private void drawLabels(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(fieldSize/2.5f));

        FontMetrics fm = g.getFontMetrics();
        for(int x = 0; x < board.getWidth(); x++) {
            String text = String.valueOf(getShipsInColumn(x));
            int textX = fieldSize*(x+1) + (fieldSize-fm.stringWidth(text))/2;
            int textY = (fieldSize-fm.getHeight())/2 + fm.getAscent();
            g.drawString(text, textX, textY);
        }

        for(int y = 0; y < board.getHeight(); y++) {
            String text = String.valueOf(getShipsInRow(y));
            int textX = (fieldSize-fm.stringWidth(text))/2;
            int textY = fieldSize*(y+1) + (fieldSize-fm.getHeight())/2 + fm.getAscent();
            g.drawString(text, textX, textY);
        }
    }

    public Point mapToImagePoint(int x, int y) {
        return new Point(fieldSize * (x + 1), fieldSize * (y + 1));
    }

    public Point mapToImagePoint(Location location) {
        return mapToImagePoint(location.getX(), location.getY());
    }

    public Location mapToBoardLocation(double x, double y) {
        int locX = (int) (x / fieldSize) - 1;
        int locY = (int) (y / fieldSize) - 1;
        if (locX >= 0 && locX < board.getWidth() && locY >= 0 && locY < board.getHeight()) {
            return new Location(locX, locY);
        }
        return null;
    }

    public Location mapToBoardLocation(Point point) {
        return mapToBoardLocation(point.getX(), point.getY());
    }

    private int calculateFieldSize() {
        int fieldWidth = (int) (size.getWidth() / (board.getWidth() + 1));
        int fieldHeight = (int) (size.getHeight() / (board.getHeight() + 1));
        return Math.min(fieldWidth,fieldHeight);
    }

    private int getShipsInRow(int row) {
        return (int) IntStream.range(0, board.getHeight())
                .mapToObj(x -> new Location(x, row))
                .map(board::getField)
                .map(Field::getState)
                .filter(FieldState.BATTLESHIP::equals)
                .count();
    }

    private int getShipsInColumn(int column) {
        return (int) IntStream.range(0, board.getWidth())
                .mapToObj(y -> new Location(column,y))
                .map(board::getField)
                .map(Field::getState)
                .filter(FieldState.BATTLESHIP::equals)
                .count();
    }
}
