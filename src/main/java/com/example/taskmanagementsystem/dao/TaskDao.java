package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.service.ProjectService;
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
            statement.setInt(5, task.getProject().getId());

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

    public Task getTask(int taskId) throws Exception, SQLException{
        String query = " SELECT * FROM task WHERE id = ?";
        Task task = null;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, taskId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // Create and set project
                ProjectService ps = new ProjectService();
                Project project = ps.getProject(rs.getInt("project_id"));

                task = new Task(rs.getInt("id"), rs.getString("title"),
                        rs.getString("description"), rs.getInt("status"),
                        rs.getObject("due_date", LocalDate.class),project
                );
            }
        }
        return task;
    }

    public ArrayList<Task> getAll() throws Exception, SQLException{
        ArrayList<Task> taskList = new ArrayList<>();
        String query = """
            SELECT t.id, t.title, t.description, t.status, t.due_date,t.project_id,
            p.name AS project_name FROM task t
            JOIN project p ON t.project_id = p.id
        """;

        try(Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                // Create and set project
                ProjectService ps = new ProjectService();
                Project project = ps.getProject(rs.getInt("project_id"));

                Task task = new Task(rs.getInt("id"), rs.getString("title"),
                        rs.getString("description"), rs.getInt("status"),
                        rs.getObject("due_date", LocalDate.class),project
                        );
                taskList.add(task);
            }
        }

        return taskList;
    }

    public void delete(int taskId) throws SQLException{
        String query = "DELETE FROM task WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, taskId);

            int rowDeleted = stmt.executeUpdate();
            if(rowDeleted > 0){
                System.out.println("Task deleted");
            }else{
                System.out.println("None");
            }
        }
    }

}
