package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.TaskDao;
import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {
    private TaskDao taskDao = new TaskDao();

    public void addTask(Task task) throws SQLException {
        taskDao.insert(task);
    }

    public Task getTask(int taskId) throws Exception, SQLException {
        if(!taskDao.checkIdExists(taskId)){
            throw new Exception("Task doesn't exists");
        }
        return taskDao.getTask(taskId);
    }

    public ArrayList<Task> getAllTasks() throws Exception, SQLException {
        return taskDao.getAll();
    }
}
