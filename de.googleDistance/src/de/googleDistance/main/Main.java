package de.googleDistance.main;

import java.io.IOException;

import de.googleDistance.coordinates.SphereCoordinates;
import de.googleDistance.json.DistanceMatrixApiService;
import de.googleDistance.scan.Scan;

public class Main {

	public static void main(String[] args) throws Exception {
		// doScan();

		// SQLiteConnection.openConnection();

		DistanceMatrixApiService.getDistance2(null, null);

		// calcSteps();
	}

	private static void doScan() throws IOException, InterruptedException {
		SphereCoordinates origin = new SphereCoordinates(48.101659, 11.668225); // Hippelstr.

		SphereCoordinates coordLU = new SphereCoordinates(48.117075, 11.645094);
		SphereCoordinates coordRU = new SphereCoordinates(48.117075, 11.690412);
		SphereCoordinates coordLD = new SphereCoordinates(48.091569, 11.645094);
		SphereCoordinates coordRD = new SphereCoordinates(48.091569, 11.690412);

		Scan scan = new Scan(origin, coordLU, coordRU, coordLD, coordRD, 50); // in
																				// meters
		scan.scan();
	}

	private static void calcSteps() {
		SphereCoordinates coordLU = new SphereCoordinates(48.117075, 11.645094);
		SphereCoordinates coordRU = new SphereCoordinates(48.117075, 11.690412);
		SphereCoordinates coordLD = new SphereCoordinates(48.091569, 11.645094);

		Long countX = 0L;
		Long countY = 0L;
		SphereCoordinates currentPoint = coordLU;
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
