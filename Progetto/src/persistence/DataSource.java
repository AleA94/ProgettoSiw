package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DataSource {
	private String dbURI = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7152991";
	private String userName = "sql7152991";
	private String password = "AAANb9dn5f";

	public DataSource() {
	}

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbURI, userName, password);

		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}