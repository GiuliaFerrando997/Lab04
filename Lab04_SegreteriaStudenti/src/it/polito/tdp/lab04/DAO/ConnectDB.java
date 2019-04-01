package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	static private final String jdbcUrl = "jdbc:mysql://localhost/iscritticorsi?useTimezone=true&serverTimezone=UTC&user=root&password=cimice97";

	public static Connection getConnection() {
		Connection connection;

		try {
				connection = DriverManager.getConnection(jdbcUrl);
			}
			
			catch (SQLException e) {
			System.err.println("Errore di connessione");
			throw new RuntimeException("Cannot get a connection " + jdbcUrl, e);
		}
		return connection;
	}

}

