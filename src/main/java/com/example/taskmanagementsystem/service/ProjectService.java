package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.dao.ProjectDao;
import com.example.taskmanagementsystem.model.Employee;
import com.example.taskmanagementsystem.model.Project;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();

    public void addProject(Project project) throws SQLException {
        projectDao.insert(project);
    }

    public void updateProject(Project project) throws Exception, SQLException {
        if(!projectDao.checkIdExists(project.getId())){
            throw new Exception("Project doesn't exists");
        }
        projectDao.update(project);
    }

    public void deleteProject(int projectId) throws Exception, SQLException {
        if(!projectDao.checkIdExists(projectId)){
            throw new Exception("Project doesn't exists");
        }
        projectDao.delete(projectId);
    }

    public ArrayList<Project> getAllProjects() throws Exception, SQLException {
        return projectDao.getAll();
    }

    public Project getProject(int projectId) throws Exception, SQLException {
        return projectDao.getProject(projectId);
    }
}
