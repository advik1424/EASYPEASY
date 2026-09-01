package com.example.CRUDSPRINGBOOTDEMO.DTO;

import jakarta.validation.constraints.*;

public class StudentRequestDTO {
@NotBlank(message = "Name cannot be null or blank")
@Size(min=2,max=50,message = "Enter the valid lenght name")

    private String name;
@Min(value = 18,message = "Age should be above then 18")
    private int age;
@NotBlank(message = "email should not be blank")
@Email(message = "email should be valid")
    private String email;
@NotNull(message = "Roll no must be required")
    private int roll_no;
@NotBlank(message = "Add the subject")
    private String subject;
@NotBlank(message = "Branch should be required")
    private String branch;
@NotNull(message = "Year should be required")
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
