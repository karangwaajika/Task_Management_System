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
			String query = """
					CREATE TABLE IF NOT EXISTS employee(
					id SERIAL PRIMARY KEY,
					email VARCHAR(16) UNIQUE,
					first_name VARCHAR(15) NOT NULL,
					last_name VARCHAR(15) NOT NULL,
					telephone_number VARCHAR(20) NOT NULL,
					sex CHAR(1) DEFAULT 'M')
				""";

			statement.execute(query);
			System.out.println("Employee table created !!!");

		}catch (SQLException e){
			logger.log(Level.ERROR, e.getMessage());
		}
	}

	public void createProjectTable(){
		try(Statement statement = this.connection.createStatement()){
			String query = """
			   CREATE TABLE IF NOT EXISTS project(
			   id SERIAL PRIMARY KEY,
			   name VARCHAR(30) UNIQUE,
			   description TEXT NOT NULL,
			   date_created TIMESTAMP NOT NULL)
			""";

			statement.execute(query);
			System.out.println("Project table created !!!");

		}catch (SQLException e){
			logger.log(Level.ERROR, e.getMessage());
		}
	}

	public void createTaskTable(){
		try(Statement statement = this.connection.createStatement()){
			String query = """
			   CREATE TABLE IF NOT EXISTS task(
			   id SERIAL PRIMARY KEY,
			   title VARCHAR(30) NOT NULL ,
			   description TEXT NOT NULL,
			   status INT DEFAULT 1,
			   due_date DATE NOT NULL,
			   project_id INT REFERENCES project(id))
			""";

			statement.execute(query);
			System.out.println("Task table created !!!");

		}catch (SQLException e){
			logger.log(Level.ERROR, e.getMessage());
		}
	}

	public void createEmployeeTaskTable(){
		try(Statement statement = this.connection.createStatement()){
			String query = """
			   CREATE TABLE IF NOT EXISTS employee_task(
			   id SERIAL PRIMARY KEY,
			   date_assigned DATE NOT NULL,
			   date_completed DATE NULL,
			   task_id INT REFERENCES task(id),
			   employee_id INT REFERENCES employee(id))
			""";

			statement.execute(query);
			System.out.println("Employee Task table created !!!");

		}catch (SQLException e){
			logger.log(Level.ERROR, e.getMessage());
		}
	}
}
