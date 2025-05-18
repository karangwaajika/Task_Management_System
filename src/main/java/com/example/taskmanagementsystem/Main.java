package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.util.DBConnection;
import com.example.taskmanagementsystem.util.SchemaCreator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DBConnection.getConnection()) {
//			Statement stmt = conn.createStatement();
//			String sql = """
//                    ALTER TABLE project ALTER COLUMN date_created SET NOT NULL""";
//            stmt.executeUpdate(sql);
			SchemaCreator sc = new SchemaCreator(conn);
			sc.createEmployeeTable();
			sc.createProjectTable();
			sc.createTaskTable();
			sc.createEmployeeTaskTable();
			System.out.println("DB connected!!");
		}catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
		}
	}


}
