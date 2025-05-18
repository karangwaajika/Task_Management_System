package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskDao {
    public void insert(Task task) throws SQLException {

        String query = """ 
            INSERT INTO task (title, description, status, due_date, project_id) VALUES(?, ?, ?, ?, ?)
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getStatus());
            statement.setObject(4, task.getDueDate());
            statement.setInt(5, task.getProjectId());

            statement.executeUpdate();

            // Get generated ID
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    task.setId(rs.getInt(1)); // update task object id.
                }
            }
            System.out.println("Task successfully inserted !!!");
        }
    }

    public boolean checkIfExists(String title, int projectId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM task WHERE title = ? && project_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setInt(2, projectId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

    public boolean checkIdExists(int id) throws SQLException{
        String sql = "SELECT COUNT(*) FROM task WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

}
