package de.googleDistance.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.googleDistance.coordinates.CartasianCoordinates;

public final class DistanceMatrixStatement extends DBStatement {

	public static void insertIntoDistanceMatrix(CartasianCoordinates start, CartasianCoordinates destination,
			long duration) throws SQLException {
		double startXValue = start.getXValue();
		double startYValue = start.getYValue();
		double destinationXValue = destination.getXValue();
		double destinationYValue = destination.getYValue();

		String statement = "INSERT INTO dm_distance_matrix (start_X, start_Y, destination_X, destination_Y, duration) VALUES ("
				+ startXValue + ", " + startYValue + ", " + destinationXValue + ", " + destinationYValue + ", "
				+ duration + ")";

		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		connStatement.executeUpdate(statement);
		connection.commit();
		connStatement.close();
	}

	public static DistanceMatrixBean selectFromDistanceMatrix(CartasianCoordinates start,
			CartasianCoordinates destination) throws SQLException {
		double startXValue = start.getXValue();
		double startYValue = start.getYValue();
		double destinationXValue = destination.getXValue();
		double destinationYValue = destination.getYValue();

		String statement = "SELECT * FROM dm_distance_matrix WHERE start_X = " + startXValue + " AND start_Y = "
				+ startYValue + " AND destination_X = " + destinationXValue + " AND destination_Y = "
				+ destinationYValue;

		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		ResultSet resultSet = connStatement.executeQuery(statement);

		if (!resultSet.next()) {
			return null;
		}

		double startX = resultSet.getDouble("start_X");
		double startY = resultSet.getDouble("start_Y");
		double destinationX = resultSet.getDouble("destination_X");
		double destinationY = resultSet.getDouble("destination_Y");
		long duration = resultSet.getLong("duration");

		resultSet.close();
		connStatement.close();

		CartasianCoordinates startResult = new CartasianCoordinates(startX, startY);
		CartasianCoordinates destinationResult = new CartasianCoordinates(destinationX, destinationY);

		return new DistanceMatrixBean(startResult, destinationResult, duration);
	}

	public static void deleteFromDistanceMatrix(CartasianCoordinates start, CartasianCoordinates destination)
			throws SQLException {
		double startXValue = start.getXValue();
		double startYValue = start.getYValue();
		double destinationXValue = destination.getXValue();
		double destinationYValue = destination.getYValue();

		String statement = "DELETE FROM dm_distance_matrix WHERE start_X = " + startXValue + " AND start_Y = "
				+ startYValue + " AND destination_X = " + destinationXValue + " AND destination_Y = "
				+ destinationYValue;

		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		connStatement.executeUpdate(statement);
		connection.commit();
		connStatement.close();
	}
}
