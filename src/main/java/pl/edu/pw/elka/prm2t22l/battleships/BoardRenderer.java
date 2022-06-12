package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;

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
    private final int boardWidth;
    private final int boardLength;
    private final BufferedImage bufferedImage;
    List<Point> positions;

    BoardRenderer(int startPointX, int startPointY, Board board) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_ARGB);
        this.positions = new ArrayList<>();
        this.fieldSize = (size.height + size.width)/20;
        this.startPointX = startPointX;
        this.startPointY = startPointY;
        this.boardWidth = board.getFields().length;
        this.boardLength = board.getFields()[0].length;
    }

    protected void saveImage() {
        Graphics g = this.bufferedImage.getGraphics();
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

        for(int i=0; i<this.boardLength; i++) {
            for (int j = 0; j < this.boardWidth; j++) {
                g2d.drawRect(startPointX + (fieldSize * j), startPointY + (fieldSize) * i, this.fieldSize, this.fieldSize);
            }
        }
        //fillField(g2d);
    }

    private void fillField(Graphics2D g2d) {
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
        if(x > startPointX && y > startPointY && x < (fieldSize*boardWidth) + startPointX && y< (fieldSize*boardLength) + startPointY) return true;
        return false;
    }
}