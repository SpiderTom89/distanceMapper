package de.googleDistance.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.googleDistance.coordinates.CartasianCoordinates;

public class TaskStatement extends DBStatement {

	public static void insertIntoTask(long taskNr, CartasianCoordinates origin, double west, double north, double east,
			double south, long accuracy, long lastAccuracy) throws SQLException {

		double originX = origin.getXValue();
		double originY = origin.getYValue();

		String statement = "INSERT INTO dm_task (task_nr, origin_x, origin_y, west, north, east, south, accuracy, last_accuracy) VALUES ("
				+ taskNr + ", " + originX + ", " + originY + ", " + west + ", " + north + ", " + east + ", " + south
				+ ", " + accuracy + ", " + lastAccuracy + ")";

		executeUpdate(statement);
	}

	public static TaskBean selectFromTask(long taskNr) throws SQLException {
		String statement = "SELECT task_nr, origin_x, origin_y, west, north, east, south, accuracy, last_accuracy FROM dm_task WHERE task_Nr = "
				+ taskNr;

		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		ResultSet resultSet = connStatement.executeQuery(statement);

		if (!resultSet.next()) {
			return null;
		}

		long taskNrResult = resultSet.getLong("task_nr");
		double originX = resultSet.getDouble("origin_x");
		double originY = resultSet.getDouble("origin_y");
		double west = resultSet.getDouble("west");
		double north = resultSet.getDouble("north");
		double east = resultSet.getDouble("east");
		double south = resultSet.getDouble("south");
		long accuracy = resultSet.getLong("accuracy");
		long lastAccuracy = resultSet.getLong("last_accuracy");

		resultSet.close();
		connStatement.close();

		CartasianCoordinates originResult = new CartasianCoordinates(originX, originY);
		return new TaskBean(taskNrResult, originResult, west, north, east, south, accuracy, lastAccuracy);
	}

	public static void deleteFromTask(long taskNr) throws SQLException {
		String statement = "DELETE FROM dm_task WHERE task_nr = " + taskNr;
		executeUpdate(statement);
	}
}
