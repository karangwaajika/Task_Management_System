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

}
