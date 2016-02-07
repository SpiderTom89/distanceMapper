package de.googleDistance.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.googleDistance.coordinates.CartasianCoordinates;

public class ProgressStatement extends DBStatement {

	public static void insertIntoProgress(long taskNr, long accuracy, CartasianCoordinates destination,
			double stepLength) throws SQLException {

		double destinationX = destination.getXValue();
		double destinationY = destination.getYValue();

		String statement = "INSERT INTO dm_progress (task_nr, accuracy, destination_x, destination_y, step_length) VALUES ("
				+ taskNr + ", " + accuracy + ", " + destinationX + ", " + destinationY + ", " + stepLength + ")";

		executeUpdate(statement);
	}

	public static ProgressBean selectFromProgress(long taskNr, long accuracy, CartasianCoordinates destination)
			throws SQLException {
		double destinationXValue = destination.getXValue();
		double destinationYValue = destination.getYValue();

		String statement = "SELECT task_nr, accuracy, destination_x, destination_y, step_length FROM dm_progress WHERE Task_Nr = "
				+ taskNr + " AND Accuracy = " + accuracy + " AND destination_X = " + destinationXValue
				+ " AND destination_Y = " + destinationYValue;

		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		ResultSet resultSet = connStatement.executeQuery(statement);

		if (!resultSet.next()) {
			return null;
		}

		long taskNrResult = resultSet.getLong("Task_Nr");
		long accuracyResult = resultSet.getLong("Accuracy");
		double destinationX = resultSet.getDouble("destination_X");
		double destinationY = resultSet.getDouble("destination_Y");
		double stepLength = resultSet.getDouble("step_length");

		resultSet.close();
		connStatement.close();

		CartasianCoordinates destinationResult = new CartasianCoordinates(destinationX, destinationY);

		return new ProgressBean(taskNrResult, accuracyResult, destinationResult, stepLength);
	}

	public static void deleteFromProgress(long taskNr, long accuracy, CartasianCoordinates destination)
			throws SQLException {
		double destinationXValue = destination.getXValue();
		double destinationYValue = destination.getYValue();

		String statement = "DELETE FROM dm_progress WHERE task_nr = " + taskNr + " AND accuracy = " + accuracy
				+ " AND destination_x = " + destinationXValue + " AND destination_y = " + destinationYValue;

		executeUpdate(statement);
	}
}
