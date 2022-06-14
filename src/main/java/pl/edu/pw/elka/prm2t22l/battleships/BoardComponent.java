package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.Board;
import pl.edu.pw.elka.prm2t22l.battleships.board.Field;
import pl.edu.pw.elka.prm2t22l.battleships.board.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class BoardComponent extends JPanel {
    private final Board board;

    public BoardComponent(Board board) {
        this.board = board;

        setSize(new Dimension(1200, 1200));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Field field = getBoardRenderer().mapToField(e.getPoint());
                if (field != null) {
                    field.setState(FieldState.BATTLESHIP);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        getBoardRenderer().renderBoard(g);
    }

    private BoardRenderer getBoardRenderer() {
        return new BoardRenderer(board, getWidth(), getHeight());
    }
}
