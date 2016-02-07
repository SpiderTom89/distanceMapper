package de.googleDistance.scan;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.googleDistance.coordinates.CartasianCoordinates;

public class ScanDivideAndConquerTest {

	@Test
	public void testStartScan() {
		CartasianCoordinates origin = new CartasianCoordinates(1, 1);
		double west = -3;
		double north = 2;
		double east = 4;
		double south = -2;
		int divideStep = 2;

		List<List<CartasianCoordinates>> coordinatesPerAccuracyStep = new LinkedList<List<CartasianCoordinates>>();
		for (int i = 0; i < divideStep; i++) {
			coordinatesPerAccuracyStep.add(new LinkedList<CartasianCoordinates>());
		}

		ScanDivideAndConquer scanDivideAndConquer = new ScanDivideAndConquer(origin, west, north, east, south,
				divideStep) {

			protected void insertDuration(CartasianCoordinates destination, long duration, int accuracyStep) {
				coordinatesPerAccuracyStep.get(accuracyStep - 1).add(destination);
			}
		};
		scanDivideAndConquer.startScan();

		List<CartasianCoordinates> actualFirstAccuracy = coordinatesPerAccuracyStep.get(0);
		List<CartasianCoordinates> actualSecondAccuracy = coordinatesPerAccuracyStep.get(1);

		List<CartasianCoordinates> expectedFirstAccuracy = getExpectedFirstAccuracy();
		List<CartasianCoordinates> expectedSecondAccuracy = getExpectedSecondAccuracy();

		Assert.assertEquals("Anzahl der Accurancy 1 Koordinaten stimmt nicht", expectedFirstAccuracy.size(),
				actualFirstAccuracy.size());
		for (CartasianCoordinates coord : expectedFirstAccuracy) {
			Assert.assertTrue("Koordinate " + coord.toString() + "nicht enthalten",
					actualFirstAccuracy.contains(coord));
		}

		Assert.assertEquals("Anzahl der Accurancy 2 Koordinaten stimmt nicht", expectedSecondAccuracy.size(),
				actualSecondAccuracy.size());
		for (CartasianCoordinates coord : expectedSecondAccuracy) {
			Assert.assertTrue("Koordinate " + coord.toString() + "nicht enthalten",
					actualSecondAccuracy.contains(coord));
		}
	}

	private List<CartasianCoordinates> getExpectedFirstAccuracy() {
		List<CartasianCoordinates> expectedFirstAccuracy = new LinkedList<CartasianCoordinates>();

		expectedFirstAccuracy.add(new CartasianCoordinates(-3, -2));
		expectedFirstAccuracy.add(new CartasianCoordinates(-1, -2));
		expectedFirstAccuracy.add(new CartasianCoordinates(1, -2));
		expectedFirstAccuracy.add(new CartasianCoordinates(3, -2));

		expectedFirstAccuracy.add(new CartasianCoordinates(-3, 0));
		expectedFirstAccuracy.add(new CartasianCoordinates(-1, 0));
		expectedFirstAccuracy.add(new CartasianCoordinates(1, 0));
		expectedFirstAccuracy.add(new CartasianCoordinates(3, 0));

		expectedFirstAccuracy.add(new CartasianCoordinates(-3, 2));
		expectedFirstAccuracy.add(new CartasianCoordinates(-1, 2));
		expectedFirstAccuracy.add(new CartasianCoordinates(1, 2));
		expectedFirstAccuracy.add(new CartasianCoordinates(3, 2));

		return expectedFirstAccuracy;
	}

	private List<CartasianCoordinates> getExpectedSecondAccuracy() {
		List<CartasianCoordinates> expectedSecondAccuracy = new LinkedList<CartasianCoordinates>();

		expectedSecondAccuracy.add(new CartasianCoordinates(-2, -2));
		expectedSecondAccuracy.add(new CartasianCoordinates(0, -2));
		expectedSecondAccuracy.add(new CartasianCoordinates(2, -2));
		expectedSecondAccuracy.add(new CartasianCoordinates(4, -2));

		expectedSecondAccuracy.add(new CartasianCoordinates(-3, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(-2, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(-1, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(0, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(1, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(2, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(3, -1));
		expectedSecondAccuracy.add(new CartasianCoordinates(4, -1));

		expectedSecondAccuracy.add(new CartasianCoordinates(-2, 0));
		expectedSecondAccuracy.add(new CartasianCoordinates(0, 0));
		expectedSecondAccuracy.add(new CartasianCoordinates(2, 0));
		expectedSecondAccuracy.add(new CartasianCoordinates(4, 0));

		expectedSecondAccuracy.add(new CartasianCoordinates(-3, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(-2, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(-1, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(0, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(1, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(2, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(3, 1));
		expectedSecondAccuracy.add(new CartasianCoordinates(4, 1));

		expectedSecondAccuracy.add(new CartasianCoordinates(-2, 2));
		expectedSecondAccuracy.add(new CartasianCoordinates(0, 2));
		expectedSecondAccuracy.add(new CartasianCoordinates(2, 2));
		expectedSecondAccuracy.add(new CartasianCoordinates(4, 2));

		return expectedSecondAccuracy;
	}
}
