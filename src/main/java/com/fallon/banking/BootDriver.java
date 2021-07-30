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



public class BootDriver {
    public static void main(String[] args) {
        User user = new User("jfallon",
                "password",
                "jfallon@mail.com",
                "James",
                "Fallon",
                LocalDate.of(1992,04,29));

        Account account = new CheckingAccount(300, "Walking around money");

        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.save(1, account);



    }
}
