package pl.edu.pw.elka.prm2t22l.battleships.gui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FramePanel extends JPanel {

    private List<ActionListener> listeners = new ArrayList<>();
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    public void changePanel(int target) {
        for (ActionListener listener:listeners) {
            listener.actionPerformed(new ChangePanelEvent(this, target));
        }
    }

}
