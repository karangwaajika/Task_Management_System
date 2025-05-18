package com.example.taskmanagementsystem.model;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
    private char sex;

    public Employee(String firstName, String lastName,
                    String email, String telephoneNumber, char sex){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.sex = sex;
    }

    public Employee(int id, String firstName, String lastName,
                    String email, String telephoneNumber, char sex){
        this(firstName, lastName, email, telephoneNumber, sex);
        this.id =  id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSex() {
        return sex;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
