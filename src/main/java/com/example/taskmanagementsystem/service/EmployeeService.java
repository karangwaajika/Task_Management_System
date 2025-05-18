package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.EmployeeDao;
import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public void registerEmployee(Employee employee) throws Exception, SQLException {
        if(employeeDao.checkIfExists(employee.getEmail())){
            throw new Exception("Employee exists already");
        }
        employeeDao.insert(employee);
    }

    public void updateEmployee(Employee employee) throws Exception, SQLException {
        if(!employeeDao.checkIdExists(employee.getId())){
            throw new Exception("Employee doesn't exists");
        }
        employeeDao.update(employee);
    }

    public void deleteEmployee(int employeeId) throws Exception, SQLException {
        if(!employeeDao.checkIdExists(employeeId)){
            throw new Exception("Employee doesn't exists");
        }
        employeeDao.delete(employeeId);
    }

    public Employee getEmployee(int employeeId) throws Exception, SQLException {
        if(!employeeDao.checkIdExists(employeeId)){
            throw new Exception("Employee doesn't exists");
        }
        return employeeDao.getEmployee(employeeId);
    }
    public ArrayList<Employee> getAllEmployees() throws Exception, SQLException {
        return employeeDao.getAll();
    }
}
