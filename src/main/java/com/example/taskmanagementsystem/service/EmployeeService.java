package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.EmployeeDao;
import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public void registerEmployee(Employee employee) throws SQLException {
        employeeDao.insert(employee);
    }

    public void updateEmployee(Employee employee) throws Exception, SQLException {
        if(!employeeDao.checkIdExists(employee.getId())){
            throw new Exception("User doesn't exists");
        }
        employeeDao.update(employee);
    }
}
