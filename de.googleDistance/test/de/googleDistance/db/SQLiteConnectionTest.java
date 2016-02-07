package de.googleDistance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class SQLiteConnectionTest {

	@Test
	public void testOpenConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		Connection c = DriverManager.getConnection("jdbc:sqlite:/D:/Quellcode/DistanceMapper/db/test.db");
		c.close();
		Assert.assertTrue("Datenbank kann nicht geschlossen werden", c.isClosed());
	}
}
