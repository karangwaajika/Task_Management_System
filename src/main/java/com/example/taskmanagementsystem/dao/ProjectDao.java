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

    public boolean checkIfExists(String name) throws SQLException{
        String sql = "SELECT COUNT(*) FROM project WHERE name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;
    }

    public boolean checkIdExists(int id) throws SQLException{
        String sql = "SELECT COUNT(*) FROM project WHERE id = ?";

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

    public ArrayList<Project> getAll() throws SQLException{
        ArrayList<Project> projectList = new ArrayList<>();
        String query = " SELECT * FROM project ";

        try(Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                Project project = new Project(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"));
                projectList.add(project);
            }
        }

        return projectList;
    }

    public Project getProject(int projectId) throws SQLException{
        String query = " SELECT * FROM project WHERE id = ?";
        Project project = null;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, projectId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                project = new Project(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"));
            }
        }
        return project;
    }

    public void update(Project project) throws SQLException{
        String query = """ 
            UPDATE project SET name = ?, description = ? WHERE id = ?
        """;
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setInt(3, project.getId());

            statement.executeUpdate();

            System.out.println("Project successfully updated !!!");
        }
    }

    public void delete(int projectId) throws SQLException{
        String query = "DELETE FROM project WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, projectId);

            int rowDeleted = stmt.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Project deleted");
            }else{
                System.out.println("None");
            }
        }
    }

}
