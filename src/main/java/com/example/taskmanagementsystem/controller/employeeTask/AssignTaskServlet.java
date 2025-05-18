package com.example.taskmanagementsystem.controller.employeeTask;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.model.EmployeeTask;
import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.service.EmployeeService;
import com.example.taskmanagementsystem.service.EmployeeTaskService;
import com.example.taskmanagementsystem.service.TaskService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "assignTask", value = "/assign_task")
public class AssignTaskServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(AssignTaskServlet.class);
    private final EmployeeTaskService employeeTaskService = new EmployeeTaskService();
    private final TaskService taskService = new TaskService();
    private final EmployeeService employeeService = new EmployeeService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime date = LocalDateTime.now();
        LocalDate today = date.toLocalDate();

        String taskId = req.getParameter("task_id");
        String employeeId = req.getParameter("employee_id");

        try{
            Task task = taskService.getTask(Integer.parseInt(taskId));
            Employee employee = employeeService.getEmployee(Integer.parseInt(employeeId));
            EmployeeTask employeeTask = new EmployeeTask(today, task, employee);
            employeeTaskService.insertAssignTask(employeeTask);
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Task assigned successfully!!"
                }
            """;

            // Set response type and encoding
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Write JSON to resp
            PrintWriter ou = resp.getWriter();
            ou.print(json);
            ou.flush();
        } catch (Exception e) {
            logger.log(Level.ERROR, e.getMessage());
            // Response Json
            String json = """
                {
                    "status": "fail",
                    "message":"""+e.getMessage()+"""
                }
            """;

            // Set response type and encoding
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Write JSON to resp
            PrintWriter ou = resp.getWriter();
            ou.print(json);
            ou.flush();
        }
    }
}
