package pl.edu.pw.elka.prm2t22l.battleships.gui.event;

import java.awt.event.ActionEvent;

public class ChangePanelEvent extends ActionEvent {
    public static final int EVENT_ID = 345;
    public static final String EVENT_COMMAND = "ChangePanel";
    private final int target;

    public ChangePanelEvent(Object source, int target) {
        super(source, EVENT_ID, EVENT_COMMAND);
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
