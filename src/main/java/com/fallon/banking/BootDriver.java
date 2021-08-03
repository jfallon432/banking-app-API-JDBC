package com.fallon.banking;

import com.fallon.banking.daos.AccountDAO;
import com.fallon.banking.daos.UserDAO;
import com.fallon.banking.models.Account;
import com.fallon.banking.models.CheckingAccount;
import com.fallon.banking.models.SavingsAccount;
import com.fallon.banking.models.User;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.time.LocalDate;


@SpringBootApplication
public class BootDriver {
    public static void main(String[] args) {
        SpringApplication.run(BootDriver.class);



    }
}
