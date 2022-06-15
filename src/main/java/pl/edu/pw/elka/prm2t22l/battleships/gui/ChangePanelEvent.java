package pl.edu.pw.elka.prm2t22l.battleships.gui;

import java.awt.event.ActionEvent;

public class ChangePanelEvent extends ActionEvent {
    private int target;

    public ChangePanelEvent(Object source, int target) {
        super(source, 345, "ChangePanel");
        this.target=target;
    }

    public int getTarget() {
        return target;
    }
}
