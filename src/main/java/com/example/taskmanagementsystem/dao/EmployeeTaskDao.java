package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.EmployeeTask;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeTaskDao {
    public void assignTask(EmployeeTask employeeTask) throws SQLException {
        String query = """ 
            INSERT INTO employee_task (date_assigned, task_id, employee_id) VALUES(?, ?, ?)
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setObject(1, employeeTask.getDateAssigned());
            statement.setInt(2, employeeTask.getTask().getId());
            statement.setInt(3, employeeTask.getEmployee().getId());

            statement.executeUpdate();

            System.out.println("EmployeeTask successfully inserted !!!");
        }
    }

    public boolean checkIfExists(int taskId, int employeeId) throws SQLException{
        String sql = "SELECT COUNT(*) FROM employee_task WHERE task_id = ? AND employee_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, taskId);
            stmt.setInt(2, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

}
