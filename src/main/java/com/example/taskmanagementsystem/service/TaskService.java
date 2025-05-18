package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.TaskDao;
import com.example.taskmanagementsystem.model.Project;
import com.example.taskmanagementsystem.model.Task;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaskService {
    private TaskDao taskDao = new TaskDao();

    public void addTask(Task task) throws Exception, SQLException {
        if(taskDao.checkIfExists(task.getTitle(), task.getProject().getId())){
            throw new Exception("Task exists already !!");
        }
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

    public void deleteTask(int taskId) throws Exception, SQLException {
        if(!taskDao.checkIdExists(taskId)){
            throw new Exception("Task doesn't exists");
        }
        taskDao.delete(taskId);
    }

    public void updateTask(Task task) throws Exception, SQLException {
        if(!taskDao.checkIdExists(task.getId())){
            throw new Exception("Task doesn't exists");
        }
        taskDao.update(task);
    }
}
