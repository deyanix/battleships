package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BoardRenderer extends JPanel implements MouseListener {
    private final int fieldSize;
    private final int startPointX;
    private final int startPointY;
    private final int boardSize;
    List<Point> positions;

    BoardRenderer() {
        addMouseListener(this);
        this.positions = new ArrayList<>();
        setPreferredSize(new Dimension(1200, 1200));
        this.fieldSize = 40;
        this.startPointX = 100;
        this.startPointY = 100;
        this.boardSize = 3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //g2d.drawRect(startPointX+ ((fieldSize/2)), startPointY + ((fieldSize/2)), this.fieldSize, this.fieldSize);

        for(int i=0; i<this.boardSize; i++) {
            for(int j=0; j<this.boardSize; j++) {
                g2d.drawRect(startPointX + (fieldSize*j), startPointY + (fieldSize)*i, this.fieldSize, this.fieldSize);
            }
        }
        drawRectangles(g2d);
        //fillField(g2d);
    }

    private void drawRectangles(Graphics2D g2d) {
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

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.add(new BoardRenderer());
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        positions.add(new Point(x,y));
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
