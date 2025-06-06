package com.example.taskmanagementsystem.controller.task;

import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.service.TaskService;
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

@WebServlet(name = "fetchTask", value = "/view_task/*")
public class TaskRetrieveOneServlet extends HttpServlet {
    private final TaskService taskService = new TaskService();
    private static final Logger logger = LogManager.getLogger(TaskRetrieveOneServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // e.g. /5
        String id = pathInfo.substring(1); // remove leading "/"

        try {
            Task task = taskService.getTask(Integer.parseInt(id));
            // Convert to JSON
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String json = mapper.writeValueAsString(task);

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
