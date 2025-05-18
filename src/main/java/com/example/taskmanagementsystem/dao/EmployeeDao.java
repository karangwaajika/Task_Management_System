package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    public void insertEmployee(Employee employee) throws SQLException {
        String query = """ 
            INSERT INTO employee (first_name, last_name, email,
            telephone_number, sex)
            VALUES(?, ?, ?, ?, ?)
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getTelephoneNumber());
            statement.setString(5, String.valueOf(employee.getSex()));

            statement.executeUpdate();

            // Get generated ID
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    employee.setId(rs.getInt(1)); // update employee object id.
                }
            }
            System.out.println("Employee successfully inserted !!!");
        }
    }
}
