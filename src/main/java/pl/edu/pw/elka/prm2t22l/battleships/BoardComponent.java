package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.List;

public class BoardComponent extends JPanel {
    private final RasterBoard board;

    private void changeFieldState(Field field) {
        if(field.getState() == FieldState.EMPTY) field.setState(FieldState.WATER);
        else if(field.getState() == FieldState.WATER) field.setState(FieldState.BATTLESHIP);
        else field.setState(FieldState.EMPTY);
    }

    public BoardComponent(RasterBoard board) {
        this.board = board;

        setSize(new Dimension(1200, 1200));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Field field = getBoardRenderer().mapToField(e.getPoint());
                if (field != null) {
                    changeFieldState(field);
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
