package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.service.ProjectService;
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

@WebServlet(name = "updateProject", value = "/update_project")
public class ProjectUpdateServlet extends HttpServlet {
    private final ProjectService projectService = new ProjectService();
    private static final Logger logger = LogManager.getLogger(ProjectUpdateServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String id = req.getParameter("id");

        Project project = new Project(Integer.parseInt(id), name, description);
        try {
            projectService.updateProject(project);
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Project updated successfully!!"
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
                    "message": "Error encountered, no update!!"
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
