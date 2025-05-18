package com.example.taskmanagementsystem.controller;

import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "updateEmployee", value = "/update_employee")
public class EmployeeUpdateServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private static final Logger logger = LogManager.getLogger(EmployeeUpdateServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String telephoneNumber = req.getParameter("telephone_number");
        String sex = req.getParameter("sex");
        String id = req.getParameter("id");

        Employee employee = new Employee(Integer.parseInt(id), firstName, lastName, email,
                telephoneNumber, sex.charAt(0));
        try {
            employeeService.updateEmployee(employee);
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Employee updated successfully!!"
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
