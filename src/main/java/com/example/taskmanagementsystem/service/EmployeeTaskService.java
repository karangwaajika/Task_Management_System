package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.EmployeeTaskDao;
import com.example.taskmanagementsystem.model.EmployeeTask;

import java.sql.SQLException;

public class EmployeeTaskService {
    private EmployeeTaskDao employeeTaskDao =  new EmployeeTaskDao();

    public void insertAssignTask(EmployeeTask employeeTask) throws Exception,SQLException {
        if(employeeTaskDao.checkIfExists(employeeTask.getTask().getId(),
                employeeTask.getEmployee().getId())){
            throw new Exception("Task already assigned to the employee");
        }
        employeeTaskDao.assignTask(employeeTask);
    }
}
