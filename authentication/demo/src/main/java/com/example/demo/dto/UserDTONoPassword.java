package com.example.demo.dto;

import java.util.Date;

public class UserDTONoPassword {

    private Date birthDate;
    private String firstName;
    private String lastName;
    private String email;
    private int id;

    public UserDTONoPassword( Date birthDate, String firstName, String lastName, String email) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTONoPassword(Date birthDate, String firstName, String lastName, String email, int id) {
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


