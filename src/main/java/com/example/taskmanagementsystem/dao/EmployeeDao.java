package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.util.DBConnection;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDao {
    public void insert(Employee employee) throws SQLException {
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

    public boolean checkIfExists(String email) throws SQLException{
        String sql = "SELECT COUNT(*) FROM employee WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

    public boolean checkIdExists(int id) throws SQLException{
        String sql = "SELECT COUNT(*) FROM employee WHERE id = ?";

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

    public void update(Employee employee) throws SQLException{
        String query = """ 
            UPDATE employee SET first_name = ?, last_name = ?,
            telephone_number = ?, sex = ? WHERE id = ?
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getTelephoneNumber());
            statement.setString(4, String.valueOf(employee.getSex()));
            statement.setInt(5, employee.getId());

            statement.executeUpdate();

            System.out.println("Employee successfully updated !!!");
        }
    }

}
