package com.example.taskmanagementsystem.controller.employee;

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

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registerEmployee", value = "/add_employee")
public class EmployeeRegistrationServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private static final Logger logger = LogManager.getLogger(EmployeeRegistrationServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String telephoneNumber = req.getParameter("telephone_number");
        char sex = req.getParameter("sex").charAt(0);

        Employee employee = new Employee(firstName, lastName, email,
                telephoneNumber, sex);
        try {
            employeeService.registerEmployee(employee);
            // Response Json
            String json = """
                {
                    "status": "success",
                    "message": "Employee Registered successfully!!"
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
