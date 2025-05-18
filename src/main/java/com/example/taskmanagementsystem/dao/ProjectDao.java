package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProjectDao {
    public void insert(Project project) throws SQLException {
        LocalDateTime date = LocalDateTime.now();
        LocalDate today = date.toLocalDate();

        String query = """ 
            INSERT INTO project (name, description, date_created) VALUES(?, ?, ?)
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setObject(3, today);

            statement.executeUpdate();

            // Get generated ID
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    project.setId(rs.getInt(1)); // update project object id.
                }
            }
            System.out.println("Project successfully inserted !!!");
        }
    }
}
