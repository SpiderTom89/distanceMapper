package de.googleDistance.db;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import de.googleDistance.coordinates.CartasianCoordinates;

public class TaskStatementTest {

	@Test
	public void testInsertSelectDeleteStatementEntry() throws SQLException {
		long taskNr = 1000;
		double originX = 10.0;
		double originY = 11.0;
		double west = 20.0;
		double north = 21.0;
		double east = 22.0;
		double south = 23.0;
		long accuracy = 10;
		long lastAccuracy = 5;

		CartasianCoordinates originCoord = new CartasianCoordinates(originX, originY);

		TaskStatement.insertIntoTask(taskNr, originCoord, west, north, east, south, accuracy, lastAccuracy);
		TaskBean taskBean = TaskStatement.selectFromTask(taskNr);

		Assert.assertNotNull("Ergebnis erwartet", taskBean);

		Assert.assertEquals("TaskNr stimmt nicht", taskNr, taskBean.getTaskNr());
		Assert.assertEquals("Origin Coordinate stimmt nicht", originCoord, taskBean.getOrigin());
		Assert.assertEquals("West stimmt nicht", west, taskBean.getWest(), 0.0);
		Assert.assertEquals("North stimmt nicht", north, taskBean.getNorth(), 0.0);
		Assert.assertEquals("East stimmt nicht", east, taskBean.getEast(), 0.0);
		Assert.assertEquals("South stimmt nicht", south, taskBean.getSouth(), 0.0);
		Assert.assertEquals("Accuracy stimmt nicht", accuracy, taskBean.getAccuracy());
		Assert.assertEquals("LastAccuracy stimmt nicht", lastAccuracy, taskBean.getLastAccuracy());

		TaskStatement.deleteFromTask(taskNr);
		TaskBean deletedTaskBean = TaskStatement.selectFromTask(taskNr);
		Assert.assertNull("Wert wurde nicht gelöscht", deletedTaskBean);
	}
}
