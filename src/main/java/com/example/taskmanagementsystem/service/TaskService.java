package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.TaskDao;
import com.example.taskmanagementsystem.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {
    private TaskDao taskDao = new TaskDao();

    public void addTask(Task task) throws SQLException {
        taskDao.insert(task);
    }
}
