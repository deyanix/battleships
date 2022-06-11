package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class BoardRenderer {
    private final int fieldSize;
    private final int startPointX;
    private final int startPointY;
    private final int boardSize;
    List<Point> positions;

    BoardRenderer(List<Point> positions, int fieldSize, int boardSize, int startPointX, int startPointY) {
        this.positions = new ArrayList<>();
        this.fieldSize = 40;
        this.startPointX = 100;
        this.startPointY = 100;
        this.boardSize = 3;
    }

    private void drawImage() {
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        renderBoard(g);

        try {
            File outputFile = new File("data/board_image.png");
            ImageIO.write(bufferedImage, "png", outputFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected void renderBoard(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<this.boardSize; i++) {
            for(int j=0; j<this.boardSize; j++) {
                g2d.drawRect(startPointX + (fieldSize*j), startPointY + (fieldSize)*i, this.fieldSize, this.fieldSize);
            }
        }
        fillFields(g2d);
    }

    private void fillFields(Graphics2D g2d) {
        int x, y;
        g2d.setColor(Color.blue);
        for (Point point : positions) {
            x = (int) point.getX();
            y = (int) point.getY();
            if(isInBoard(x,y)) {
                int multiplicatorX = (x - startPointX) / this.fieldSize;
                int multiplicatorY = (y - startPointY) / this.fieldSize;
                g2d.fillRect(startPointX + fieldSize * multiplicatorX, startPointY + fieldSize * multiplicatorY, this.fieldSize, this.fieldSize);
            }
        }
    }

    private boolean isInBoard(int x, int y) {
        if(x > startPointX && y > startPointY && x < (fieldSize*boardSize) + startPointX && y< (fieldSize*boardSize) + startPointY) return true;
        return false;
    }
}