package br.com.jvp.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionFactory {

	
	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
	}
	
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
	}

	private static Connection initConnection() {
		try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ex29", "postgres", "admin"
            		);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}