package pl.edu.pw.elka.prm2t22l.battleships;

import java.awt.*;
import javax.swing.*;

public class BoardField extends JPanel {
    private final int fieldSize;
    private final int startPointX;
    private final int startPointY;
    private final int boardSize;

    BoardField() {
        setPreferredSize(new Dimension(400, 400));
        this.fieldSize = 50;
        this.startPointX = 40;
        this.startPointY = 40;
        this.boardSize = 5;
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
    }

    public static void main(String[] args) {
        BoardField b = new BoardField();
        JFrame f = new JFrame();
        f.add(b);
        f.setSize(400,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
