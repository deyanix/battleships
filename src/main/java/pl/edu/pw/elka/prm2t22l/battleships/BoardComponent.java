package pl.edu.pw.elka.prm2t22l.battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class BoardComponent extends JPanel implements MouseListener {
    private final List<Point> positions;

    BoardComponent() {
        addMouseListener(this);
        this.positions = new ArrayList<>();
        this.setVisible(true);
        setPreferredSize(new Dimension(1200, 1200));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BoardRenderer boardRenderer = new BoardRenderer(positions,40,5,100,100);
        boardRenderer.renderBoard(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.add(new BoardComponent());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
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
