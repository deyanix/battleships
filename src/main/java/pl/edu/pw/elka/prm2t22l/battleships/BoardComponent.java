package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Field;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class BoardComponent extends JPanel {
    private final GamePlayManager manager;

    public BoardComponent(GamePlayManager manager) {
        this.manager = manager;

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

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved( MouseEvent e ) {
                Field field = getBoardRenderer().mapToField(e.getPoint());
                if (field != null && !field.isImmutable()) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        getBoardRenderer().renderBoard(g);
    }

    private void changeFieldState(Field field) {
        if (!field.isImmutable()) {
            switch (field.getState()) {
                case EMPTY -> manager.turn(field.getLocation(), FieldState.WATER);
                case WATER -> manager.turn(field.getLocation(), FieldState.BATTLESHIP);
                case BATTLESHIP -> manager.turn(field.getLocation(), FieldState.EMPTY);
            }
        }
    }

    private BoardRenderer getBoardRenderer() {
        return new BoardRenderer(manager.getBoard(), getWidth(), getHeight());
    }
}
