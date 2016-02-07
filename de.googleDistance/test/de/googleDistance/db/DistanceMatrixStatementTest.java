package de.googleDistance.db;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import de.googleDistance.coordinates.CartasianCoordinates;

public class DistanceMatrixStatementTest {

	@Test
	public void testInsertSelectDeleteDistanceMatrixEntry() throws SQLException {
		double startXValue = 10.0;
		double startYValue = 11.0;
		double destinationXValue = 20.0;
		double destinationYValue = 21.0;
		long duration = 100;

		CartasianCoordinates startCoord = new CartasianCoordinates(startXValue, startYValue);
		CartasianCoordinates destinationCoord = new CartasianCoordinates(destinationXValue, destinationYValue);

		DistanceMatrixStatement.insertIntoDistanceMatrix(startCoord, destinationCoord, duration);
		DistanceMatrixBean distanceMatrixBean = DistanceMatrixStatement.selectFromDistanceMatrix(startCoord,
				destinationCoord);

		Assert.assertNotNull("Ergebnis erwartet", distanceMatrixBean);

		Assert.assertEquals("Start Coordinate stimmt nicht", startCoord, distanceMatrixBean.getStart());
		Assert.assertEquals("Destination Coordinate stimmt nicht", destinationCoord,
				distanceMatrixBean.getDestination());
		Assert.assertEquals("Duration stimmt nicht", duration, distanceMatrixBean.getDuration(), 0.0);

		DistanceMatrixStatement.deleteFromDistanceMatrix(startCoord, destinationCoord);

		DistanceMatrixBean deletedDistanceMatrixBean = DistanceMatrixStatement.selectFromDistanceMatrix(startCoord,
				destinationCoord);

		Assert.assertNull("Wert wurde nicht gelöscht", deletedDistanceMatrixBean);
	}
}
