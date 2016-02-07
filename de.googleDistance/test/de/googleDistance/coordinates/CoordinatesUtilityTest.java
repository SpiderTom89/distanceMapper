package de.googleDistance.coordinates;

import org.junit.Assert;
import org.junit.Test;

public class CoordinatesUtilityTest {

	// TODO noch bisschen bessere Test schreiben

	@Test
	public void testCalcSphereCoordWithZero() {
		CartasianCoordinates cartasianCoord = new CartasianCoordinates(0.0, 0.0);
		SphereCoordinates sphereCoordinates = CoordinatesUtility.calcSphereCoord(cartasianCoord);

		Assert.assertEquals("Latitue Koordinate stimmt nicht", 0.0, sphereCoordinates.getLat(), 0.0);
		Assert.assertEquals("Longitue Koordinate stimmt nicht", 0.0, sphereCoordinates.getLng(), 0.0);
	}

	@Test
	public void testCalcCartasianCoordWithZero() {
		SphereCoordinates sphereCoordinates = new SphereCoordinates(0.0, 0.0);
		CartasianCoordinates calcCartasianCoord = CoordinatesUtility.calcCartasianCoord(sphereCoordinates);

		Assert.assertEquals("X Koordinate stimmt nicht", 0.0, calcCartasianCoord.getXValue(), 0.0);
		Assert.assertEquals("Y Koordinate stimmt nicht", 0.0, calcCartasianCoord.getYValue(), 0.0);
	}
}
