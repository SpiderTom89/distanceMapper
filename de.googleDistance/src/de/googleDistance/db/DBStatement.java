package de.googleDistance.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DBStatement {

	String m_statement;

	public DBStatement() {
		this(null);
	}

	public DBStatement(String statement) {
		m_statement = statement;
	}

	public String getStatement() {
		return m_statement;
	}

	public void setStatement(String statement) {
		m_statement = statement;
	}

	public final void executeStatement(Connection connection) throws SQLException {
		Statement createStatement = connection.createStatement();
		createStatement.executeQuery(getStatement());
	}

	public final static void executeUpdate(String statement) throws SQLException {
		Connection connection = SQLiteConnection.getConnection();
		Statement connStatement = connection.createStatement();
		connStatement.executeUpdate(statement);
		connection.commit();
		connStatement.close();
	}
}
