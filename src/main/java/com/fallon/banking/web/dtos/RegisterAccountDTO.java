package com.fallon.banking.web.dtos;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class RegisterAccountDTO {


    @NotEmpty
    private String email;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;


    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private LocalDate dob;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }
}
