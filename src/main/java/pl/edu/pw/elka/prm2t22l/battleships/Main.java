package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;
import pl.edu.pw.elka.prm2t22l.battleships.generator.BoardGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		GameConfiguration configuration = new GameConfiguration();
		configuration.setBoardSize(6, 6);
		configuration.setShipAmount(ShipType.LONG, 1);
		configuration.setShipAmount(ShipType.MEDIUM, 2);
		configuration.setShipAmount(ShipType.SHORT, 3);
		configuration.setSeed(1);

		BoardGenerator generator = new BoardGenerator(configuration);
		generator.generate();
		generator.getRasterBoard().display();
	}

}
