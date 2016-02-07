package de.googleDistance.main;

import java.io.IOException;

import de.googleDistance.db.SQLiteConnection;
import de.googleDistance.rest.Coordinates;
import de.googleDistance.scan.Scan;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// doScan();

		SQLiteConnection.openConnection();

		// calcSteps();
	}

	private static void doScan() throws IOException, InterruptedException {
		Coordinates origin = new Coordinates(48.101659, 11.668225); // Hippelstr.

		Coordinates coordLU = new Coordinates(48.117075, 11.645094);
		Coordinates coordRU = new Coordinates(48.117075, 11.690412);
		Coordinates coordLD = new Coordinates(48.091569, 11.645094);
		Coordinates coordRD = new Coordinates(48.091569, 11.690412);

		Scan scan = new Scan(origin, coordLU, coordRU, coordLD, coordRD, 50); // in
																				// meters
		scan.scan();
	}

	private static void calcSteps() {
		Coordinates coordLU = new Coordinates(48.117075, 11.645094);
		Coordinates coordRU = new Coordinates(48.117075, 11.690412);
		Coordinates coordLD = new Coordinates(48.091569, 11.645094);

		Long countX = 0L;
		Long countY = 0L;
		Coordinates currentPoint = coordLU;
		Scan scan = new Scan();

		while (currentPoint.isAboveOf(coordLD)) {
			while (currentPoint.isLeftOf(coordRU)) {
				currentPoint = scan.getShiftedCoordinates(currentPoint, 0, 50);
				countX++;
			}
			currentPoint = scan.getShiftedCoordinates(currentPoint, 50, 0);
			countY++;
		}
		System.out.println(countX);
		System.out.println(countY);
	}
}
