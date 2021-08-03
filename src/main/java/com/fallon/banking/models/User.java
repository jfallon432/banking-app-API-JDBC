package com.fallon.banking.models;

import com.fallon.banking.web.dtos.RegisterAccountDTO;

import java.time.LocalDate;
import java.util.Map;

public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Map accounts;

    public User(String username, String password, String email, String firstName, String lastName, LocalDate dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public User(int id, String username, String password, String email, String firstName, String lastName, LocalDate dob) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public User() {
    }

    public User(RegisterAccountDTO registerAccountDTO){
        this.email = registerAccountDTO.getEmail();
        this.username = registerAccountDTO.getUsername();
        this.password =registerAccountDTO.getPassword();
        this.firstName = registerAccountDTO.getFirstName();
        this.lastName = registerAccountDTO.getLastName();
        this.dob = registerAccountDTO.getDob();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Map getAccounts() {
        return accounts;
    }

    public void setAccounts(Map accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                ", accounts=" + accounts +
                '}';
    }
}
