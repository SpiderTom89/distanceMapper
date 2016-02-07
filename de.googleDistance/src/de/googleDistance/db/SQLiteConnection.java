package de.googleDistance.db;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SQLiteConnection {

	private SQLiteConnection() {
	}

	public static void openConnection() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:/D:/Quellcode/DistanceMapper/db/test.db");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
}