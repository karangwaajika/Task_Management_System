package com.example.taskmanagementsystem.controller.employeeTask;

import com.example.taskmanagementsystem.model.EmployeeTask;
import com.example.taskmanagementsystem.service.EmployeeTaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.List;

@WebServlet(name = "fetchAssignTasks", value = "/view_assign_tasks")
public class AssignTaskRetrieveAllServlet extends HttpServlet {
    private final EmployeeTaskService taskService = new EmployeeTaskService();
    private static final Logger logger = LogManager.getLogger(AssignTaskRetrieveAllServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<EmployeeTask> tasks = taskService.getAllAssignedTasks();
            // Convert to JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String json = mapper.writeValueAsString(tasks);

            // Set response type
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

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
