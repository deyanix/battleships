package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BoardField extends JPanel implements MouseListener {
    private final int fieldSize;
    private final int startPointX;
    private final int startPointY;
    private final int boardSize;
    private int flag = 0;
    List<Point> positions;

    BoardField() {
        addMouseListener(this);
        this.positions = new ArrayList<>();
        setPreferredSize(new Dimension(1200, 1200));
        this.fieldSize = 70;
        this.startPointX = 100;
        this.startPointY = 40;
        this.boardSize = 6;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<this.boardSize; i++) {
            for(int j=0; j<this.boardSize; j++) {
                g2d.drawRect(startPointX+ ((fieldSize/2)*j), startPointY + ((fieldSize/2)*i), this.fieldSize, this.fieldSize);
            }
        }
        drawRectangles(g2d);
    }

    private void drawRectangles(Graphics2D g2d) {
        int x, y;
        g2d.setColor(Color.blue);
        for (Point point : positions) {
            x = (int) point.getX();
            y = (int) point.getY();
            int multiplicatorX = getMultiplicatorX(x)/2;
            int multiplicatorY = getMultiplicatorY(y)/2;
            g2d.fillRect(startPointX + multiplicatorX*fieldSize, startPointY + multiplicatorY*fieldSize, this.fieldSize/2,this.fieldSize/2);
        }
    }

    private int getMultiplicatorX(int x) {
        if(x<startPointX) return 0;
        int multipX = x / this.fieldSize;
        return multipX;
    }

    private int getMultiplicatorY(int y) {
        if(y<startPointX) return 0;
        int multipY = y / this.fieldSize;
        return multipY;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.add(new BoardField());
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
