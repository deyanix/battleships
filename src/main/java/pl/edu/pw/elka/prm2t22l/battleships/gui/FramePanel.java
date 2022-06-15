package pl.edu.pw.elka.prm2t22l.battleships.gui;

import pl.edu.pw.elka.prm2t22l.battleships.gui.event.ChangePanelEvent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FramePanel extends JPanel {
    private final List<ActionListener> listeners = new ArrayList<>();
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    protected void invokeEvent(ActionEvent event) {
        for (ActionListener listener : listeners) {
            listener.actionPerformed(event);
        }
    }

    protected void changePanel(int target) {
        invokeEvent(new ChangePanelEvent(this, target));
    }

}
