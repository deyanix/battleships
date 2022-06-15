package pl.edu.pw.elka.prm2t22l.battleships.gui.event;

import pl.edu.pw.elka.prm2t22l.battleships.GameConfiguration;
import pl.edu.pw.elka.prm2t22l.battleships.GamePlayManager;

import java.awt.event.ActionEvent;

public class PlayEvent extends ActionEvent {
	public static final int EVENT_ID = 346;
	public static final String EVENT_COMMAND = "PlayEvent";
	private final GamePlayManager gamePlayManager;

	public PlayEvent(Object source, GamePlayManager gamePlayManager) {
		super(source, EVENT_ID, EVENT_COMMAND);
		this.gamePlayManager = gamePlayManager;
	}

	public GamePlayManager getManager() {
		return gamePlayManager;
	}
}
