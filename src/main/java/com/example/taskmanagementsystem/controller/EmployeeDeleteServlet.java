package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.service.EmployeeService;
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

@WebServlet(name = "deleteEmployee", value = "/delete_employee")
public class EmployeeDeleteServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private static final Logger logger = LogManager.getLogger(EmployeeDeleteServlet.class);
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            employeeService.deleteEmployee(Integer.parseInt(id));
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Employee deleted successfully !!"
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
