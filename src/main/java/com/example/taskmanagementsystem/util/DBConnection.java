package com.example.taskmanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String URL = "jdbc:postgresql://localhost:5432/task";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1l0v3";


	static {
		try {
			// Explicitly load the PostgreSQL driver
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError("PostgreSQL JDBC driver not found.");
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
