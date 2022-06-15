package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.board.RasterBoard;
import pl.edu.pw.elka.prm2t22l.battleships.board.VectorBoard;
import pl.edu.pw.elka.prm2t22l.battleships.entity.FieldState;
import pl.edu.pw.elka.prm2t22l.battleships.entity.Ship;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;
import pl.edu.pw.elka.prm2t22l.battleships.generator.BoardGenerator;
import pl.edu.pw.elka.prm2t22l.battleships.gui.InterfaceMainMenu;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) throws Exception {
		GameConfiguration configuration = new GameConfiguration();
		configuration.setBoardSize(6, 6);
		configuration.setShipAmount(ShipType.LONG, 1);
		configuration.setShipAmount(ShipType.MEDIUM, 1);
		configuration.setShipAmount(ShipType.SHORT, 1);
		configuration.setSeed(ThreadLocalRandom.current().nextLong());

		for (int i = 0; i < 100000; i++) {
			BoardGenerator generator = new BoardGenerator(configuration);
			if (generator.generate()) {
				System.out.println("test");
			}
		}

	}

}
