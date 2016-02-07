package de.googleDistance.db;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import de.googleDistance.coordinates.CartasianCoordinates;

public class ProgressStatementTest {

	@Test
	public void testInsertSelectDeleteProgressEntry() throws SQLException {
		long taskNr = -1;
		long accuracy = 10;
		double destinationX = 10.0;
		double destinationY = 11.0;
		double stepLength = 20.0;

		CartasianCoordinates destinationCoord = new CartasianCoordinates(destinationX, destinationY);

		ProgressStatement.insertIntoProgress(taskNr, accuracy, destinationCoord, stepLength);
		ProgressBean progressBean = ProgressStatement.selectFromProgress(taskNr, accuracy, destinationCoord);

		Assert.assertNotNull("Ergebnis erwartet", progressBean);

		Assert.assertEquals("TaskNr stimmt nicht", taskNr, progressBean.getTaskNr());
		Assert.assertEquals("Accuracy stimmt nicht", accuracy, progressBean.getAccuracy());
		Assert.assertEquals("Destination Coordinate stimmt nicht", destinationCoord, progressBean.getDestination());
		Assert.assertEquals("StepLength stimmt nicht", stepLength, progressBean.getStepLength(), 0.0);

		ProgressStatement.deleteFromProgress(taskNr, accuracy, destinationCoord);
		ProgressBean deletedProgressBean = ProgressStatement.selectFromProgress(taskNr, accuracy, destinationCoord);
		Assert.assertNull("Wert wurde nicht gelöscht", deletedProgressBean);
	}
}
