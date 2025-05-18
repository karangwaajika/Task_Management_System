package com.example.taskmanagementsystem.dao;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.model.EmployeeTask;
import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.service.EmployeeService;
import com.example.taskmanagementsystem.service.ProjectService;
import com.example.taskmanagementsystem.service.TaskService;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

            // Get generated ID
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    employeeTask.setId(rs.getInt(1)); // update employee task object id.
                }
            }

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

    public ArrayList<EmployeeTask> getAll() throws Exception, SQLException{
        ArrayList<EmployeeTask> assignedTaskList = new ArrayList<>();
        String query = """
            SELECT et.id, et.date_assigned, et.date_completed,
            et.task_id, et.employee_id FROM employee_task et
            JOIN task t ON et.task_id = t.id JOIN employee e ON et.employee_id = e.id
        """;

        try(Connection conn = DBConnection.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query)){

            while (rs.next()) {
                // create task
                TaskService taskService = new TaskService();
                Task task = taskService.getTask(rs.getInt("task_id"));
                // create employee
                EmployeeService employeeService = new EmployeeService();
                Employee employee = employeeService.getEmployee(rs.getInt("employee_id"));

                EmployeeTask assignedTask = new EmployeeTask(rs.getInt("id"),
                        rs.getObject("date_completed", LocalDate.class),
                        rs.getObject("date_assigned", LocalDate.class), task, employee);

                assignedTaskList.add(assignedTask);
            }
        }
        return assignedTaskList;
    }

}
