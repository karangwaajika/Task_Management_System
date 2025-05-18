package com.example.taskmanagementsystem.controller.task;

import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.model.Task;
import com.example.taskmanagementsystem.service.ProjectService;
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

@WebServlet(name ="addTask", value = "/add_task")
public class TaskAddServlet extends HttpServlet {
    private final TaskService taskService = new TaskService();
    private final ProjectService projectService = new ProjectService();
    private final Logger logger = LogManager.getLogger(TaskAddServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title =  req.getParameter("title");
        String description =  req.getParameter("description");
        String status =  req.getParameter("status");
        String date =  req.getParameter("due_date");
        String projectId =  req.getParameter("project_id");
        LocalDate dueDate = LocalDate.parse(date);



        try{
            Project project = projectService.getProject(Integer.parseInt(projectId));
            Task task = new Task(title, description, Integer.parseInt(status), dueDate,
                    project);
            taskService.addTask(task);
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Task Added successfully!!"
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
