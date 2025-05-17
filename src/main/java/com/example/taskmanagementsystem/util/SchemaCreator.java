package com.example.taskmanagementsystem.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaCreator {
	private final Connection connection;
	private static final Logger logger = LogManager.getLogger(SchemaCreator.class);
	public SchemaCreator(Connection connection){
		this.connection = connection;
	}
	public void createEmployeeTable(){
		try(Statement statement = this.connection.createStatement()){
			String query = "CREATE TABLE IF NOT EXISTS employee(" +
						   "id SERIAL PRIMARY KEY, " +
						   "email VARCHAR(16) UNIQUE, " +
						   "first_name VARCHAR(15) NOT NULL, " +
						   "last_name VARCHAR(15) NOT NULL, " +
						   "telephone_number VARCHAR(20) NOT NULL, " +
						   "sex CHAR(1) DEFAULT 'M')";

			statement.execute(query);
			System.out.println("Employee table created !!!");

		}catch (SQLException e){
			logger.log(Level.ERROR, e.getMessage());
		}
	}
}
