package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.GameBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardRenderer {
    private static final Image WATER_IMAGE;

    static {
        try {
            WATER_IMAGE = ImageIO.read(new File("src/main/resources/water-waves.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final GameBoard board;
    private final Dimension size;
    private final int fieldSize;
    private final static float FIELD_PADDING = 0.15f;
    private final static float FIELD_ROUND = 0.25f;

    public BoardRenderer(GameBoard board, Dimension size) {
        this.board = board;
        this.size = size;
        this.fieldSize = calculateFieldSize();
    }

    public BoardRenderer(GameBoard board, int width, int height) {
        this(board, new Dimension(width, height));
    }

    public BoardRenderer(GameBoard board) {
        this(board, new Dimension((board.getWidth()+1) * 50 + 1, (board.getHeight()+1) * 50 + 1));
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
        for (Field field : board.getPlayerBoard()) {
            Point point = mapToImagePoint(field.getLocation());
            if (field.getState() == FieldState.WATER) {
                g.drawImage(WATER_IMAGE,
                        (int) (point.getX() + fieldSize * FIELD_PADDING / 2),
                        (int) (point.getY() + fieldSize * FIELD_PADDING / 2),
                        (int) (fieldSize * (1 - FIELD_PADDING)),
                        (int) (fieldSize * (1 - FIELD_PADDING)),
                        null);
            } else if(field.getState() == FieldState.BATTLESHIP) {
                g.setColor(Color.BLACK);
                g.fillRoundRect(
                        (int) (point.getX() + fieldSize * FIELD_PADDING / 2),
                        (int) (point.getY() + fieldSize * FIELD_PADDING / 2),
                        (int) (fieldSize * (1 - FIELD_PADDING)),
                        (int) (fieldSize * (1 - FIELD_PADDING)),
                        (int) (fieldSize * FIELD_ROUND),
                        (int) (fieldSize * FIELD_ROUND));
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

    public Field mapToField(double x, double y) {
        int locX = (int) (x / fieldSize) - 1;
        int locY = (int) (y / fieldSize) - 1;
        return board.getPlayerBoard().getField(locX, locY);
    }

    public Field mapToField(Point point) {
        return mapToField(point.getX(), point.getY());
    }

    private int calculateFieldSize() {
        int fieldWidth = (int) (size.getWidth() / (board.getWidth() + 1));
        int fieldHeight = (int) (size.getHeight() / (board.getHeight() + 1));
        return Math.min(fieldWidth,fieldHeight);
    }

    private int getShipsInRow(int row) {
        return (int) IntStream.range(0, board.getWidth())
                .mapToObj(x -> new Location(x, row))
                .map(board.getComputedBoard()::getField)
                .map(Field::getState)
                .filter(FieldState.BATTLESHIP::equals)
                .count();
    }

    private int getShipsInColumn(int column) {
        return (int) IntStream.range(0, board.getHeight())
                .mapToObj(y -> new Location(column, y))
                .map(board.getComputedBoard()::getField)
                .map(Field::getState)
                .filter(FieldState.BATTLESHIP::equals)
                .count();
    }
}
