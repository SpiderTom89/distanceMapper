package de.googleDistance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SQLiteConnection {

	private static Connection m_connection;

	private SQLiteConnection() {
	}

	public static Connection getConnection() throws SQLException {
		if (m_connection == null) {
			openConnection();
		}
		return m_connection;
	}

	public static void openConnection() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // TODO noch schöner lösen
		}
		m_connection = DriverManager.getConnection("jdbc:sqlite:\\D:\\Quellcode\\DistanceMapper\\db\\test.db");
		m_connection.setAutoCommit(false);
		System.out.println("D:/Quellcode/DistanceMapper/db/test.db opened successfully");
	}
}