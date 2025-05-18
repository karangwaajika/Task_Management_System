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
}
