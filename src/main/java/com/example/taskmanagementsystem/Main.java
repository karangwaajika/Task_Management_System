package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.util.DBConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		try (Connection conn = DBConnection.getConnection()) {
			System.out.println("DB connected!!");
		}catch (SQLException e) {
			logger.log(Level.ERROR, e.getMessage());
		}
	}


}
