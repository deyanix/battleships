package pl.edu.pw.elka.prm2t22l.battleships;

import pl.edu.pw.elka.prm2t22l.battleships.entity.Location;
import pl.edu.pw.elka.prm2t22l.battleships.entity.ShipType;
import pl.edu.pw.elka.prm2t22l.battleships.generator.BoardGenerator;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		GameConfiguration configuration = new GameConfiguration();
		configuration.setBoardSize(6, 6);
		configuration.setShipAmount(ShipType.LONG, 1);
		configuration.setShipAmount(ShipType.MEDIUM, 2);
		configuration.setShipAmount(ShipType.SHORT, 3);
		configuration.setSeed(ThreadLocalRandom.current().nextLong());

		BoardGenerator generator = new BoardGenerator(configuration);
		while (!generator.generate());

		JFrame frame = new JFrame();
		frame.setSize(600,600);
		frame.setContentPane(new BoardComponent(generator.getRasterBoard()));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
