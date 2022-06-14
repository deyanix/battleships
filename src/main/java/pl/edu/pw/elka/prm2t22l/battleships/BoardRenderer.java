package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.Field;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;

public class BoardRenderer {
    private static final int PADDING = 60;
    private static final float FONT_SIZE = 50;
    private static final int PADDING_TEXT = 60;
    private final Board board;

    BoardRenderer(Board board) {
        this.board = board;
    }

    protected void saveImage(int width, int height, File file) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        renderBoard(width, height, g);
        ImageIO.write(bufferedImage, "png", file);
    }


    protected void renderBoard(int width, int height, Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // TODO: Usunąć 2D
        int fieldSize = calculateFieldSize(width, height);
        fillField(g2d,fieldSize);
        drawSkeleton(g2d,fieldSize);
        drawLabels(g,fieldSize);
    }

    private void drawSkeleton(Graphics2D g2d, int fieldSize) {
        g2d.setColor(Color.BLACK);
        for(int y=0; y<this.board.getHeight(); y++) {
            for (int x = 0; x < this.board.getWidth(); x++) {
                g2d.drawRect(PADDING + (fieldSize * x), PADDING + (fieldSize) * y, fieldSize, fieldSize);
            }
        }
    }

    private int calculateFieldSize(int width, int height) {
        int fieldWidth = (width - PADDING)/ board.getWidth();
        int fieldHeight = (height - PADDING)/ board.getHeight();
        return Math.min(fieldWidth,fieldHeight);
    }

    private void fillField(Graphics2D g2d, int fieldSize) {
        for(Field field : board) {
            Point point = mapToImagePoint(field.getLocation(), fieldSize);
            if(field.getState() == FieldState.WATER) {
                g2d.setColor(Color.BLUE);
                g2d.fillRect((int) point.getX(), (int) point.getY(), fieldSize, fieldSize);
            } else if(field.getState() == FieldState.BATTLESHIP) {
                g2d.setColor(Color.BLACK);
                g2d.fillRect((int) point.getX(), (int) point.getY(), fieldSize, fieldSize);
            }
        }
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



    private void drawLabels(Graphics g, int fieldSize) {
        g.setColor(Color.BLACK);
        g.setFont(g.getFont().deriveFont(FONT_SIZE));
        for(int x=0;x< board.getWidth();x++) {
            int ships = getShipsInColumn(x);
            g.drawString(String.valueOf(ships),PADDING + fieldSize*x, PADDING_TEXT);
        }
        for(int y=0; y< board.getHeight(); y++) {
            int ships = getShipsInRow(y);
            g.drawString(String.valueOf(ships),PADDING_TEXT/2, PADDING*2 + fieldSize*y);
        }
    }

    private Point mapToImagePoint(int x, int y, int fieldSize) {
        return new Point(PADDING + (fieldSize * x), PADDING + (fieldSize * y));
    }

    private Point mapToImagePoint(Location location, int fieldSize) {
        return mapToImagePoint(location.getX(), location.getY(),fieldSize);
    }
}