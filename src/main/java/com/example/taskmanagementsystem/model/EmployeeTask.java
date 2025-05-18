package com.example.taskmanagementsystem.model;

import java.time.LocalDate;

public class EmployeeTask {
    private LocalDate dateAssigned;
    private LocalDate dateComplete;
    private Task task;
    private Employee employee;

    public EmployeeTask(LocalDate dateAssigned, Task task, Employee employee){
        this.dateAssigned = dateAssigned;
        this.task = task;
        this.employee = employee;
    }

    public EmployeeTask(LocalDate dateComplete, LocalDate dateAssigned, Task task, Employee employee){
        this(dateAssigned, task, employee);
        this.dateComplete = dateComplete;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setDateAssigned(LocalDate dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public LocalDate getDateAssigned() {
        return dateAssigned;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setDateComplete(LocalDate dateComplete) {
        this.dateComplete = dateComplete;
    }

    public LocalDate getDateComplete() {
        return dateComplete;
    }
}
