package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.EmployeeTaskDao;
import com.example.taskmanagementsystem.model.EmployeeTask;

import java.sql.SQLException;

public class EmployeeTaskService {
    private EmployeeTaskDao employeeTaskDao =  new EmployeeTaskDao();

    public void insertAssignTask(EmployeeTask employeeTask) throws SQLException {
        employeeTaskDao.assignTask(employeeTask);
    }
}
