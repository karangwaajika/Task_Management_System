package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.EmployeeTaskDao;
import com.example.taskmanagementsystem.model.EmployeeTask;
import com.example.taskmanagementsystem.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeTaskService {
    private EmployeeTaskDao employeeTaskDao =  new EmployeeTaskDao();

    public void insertAssignTask(EmployeeTask employeeTask) throws Exception,SQLException {
        if(employeeTaskDao.checkIfExists(employeeTask.getTask().getId(),
                employeeTask.getEmployee().getId())){
            throw new Exception("Task already assigned to this employee");
        }
        employeeTaskDao.assignTask(employeeTask);
    }

    public ArrayList<EmployeeTask> getAllAssignedTasks() throws Exception, SQLException {
        return employeeTaskDao.getAll();
    }
}
