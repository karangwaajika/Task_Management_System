package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name = "fetchProjects", value = "/view_projects")
public class ProjectRetrieveAllServlet extends HttpServlet {
    private final ProjectService projectService = new ProjectService();
    private static final Logger logger = LogManager.getLogger(ProjectRetrieveAllServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Project> projects = projectService.getAllProjects();
            // Convert to JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(projects);

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
                    "message": "Error encountered !!"
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
