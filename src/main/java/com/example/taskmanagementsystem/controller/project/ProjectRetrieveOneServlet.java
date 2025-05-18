package com.example.taskmanagementsystem.controller.project;

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

@WebServlet(name = "fetchProject", value = "/view_project/*")
public class ProjectRetrieveOneServlet extends HttpServlet {
    private final ProjectService projectService = new ProjectService();
    private static final Logger logger = LogManager.getLogger(ProjectRetrieveOneServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo(); // e.g. /5
        String id = pathInfo.substring(1); // remove leading "/"

        try {
            Project project = projectService.getProject(Integer.parseInt(id));
            // Convert to JSON
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(project);

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
                    "message":"""+ e.getMessage() +"""
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
