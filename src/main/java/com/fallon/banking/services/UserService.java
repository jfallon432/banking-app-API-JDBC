package com.fallon.banking.services;


import com.fallon.banking.daos.UserDAO;
import com.fallon.banking.exceptions.InvalidRequestException;
import com.fallon.banking.exceptions.ResourcePersistenceException;
import com.fallon.banking.models.User;
import com.fallon.banking.util.ConnectionFactory;
import com.fallon.banking.web.dtos.AuthenticatedDTO;
import com.fallon.banking.web.dtos.LoginAccountDTO;
import com.fallon.banking.web.dtos.RegisterAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    @Transactional
    public void register(RegisterAccountDTO registerAccountDTO) throws ResourcePersistenceException, InvalidRequestException, SQLException {
        User newUser = new User(registerAccountDTO);
        if (isEmailTaken.test(userDAO, newUser)) throw new ResourcePersistenceException("this email is taken");
        if (isUsernameTaken.test(userDAO, newUser)) throw new ResourcePersistenceException("this username is taken");

        if (isUserValid(newUser)) {
            userDAO.save(newUser);
        }

    }

    @Transactional
    public AuthenticatedDTO login(LoginAccountDTO loginAccountDTO) throws SQLException {
        User user = userDAO.getUserByUsername(loginAccountDTO.getUsername());
        if (user == null) throw new InvalidRequestException("User not found");
        User user2 = userDAO.getUserByPassword(loginAccountDTO.getPassword(), loginAccountDTO.getUsername());
        if(user.getUsername().equals(user2.getUsername()) && user.getPassword().equals(user2.getPassword())){
            AuthenticatedDTO authenticatedDTO = new AuthenticatedDTO(user);
            return authenticatedDTO;
        }else throw new InvalidRequestException("wrong password");




    }

    private boolean isUserValid(User user) throws InvalidRequestException {
        if (!isEmailValid.test(user)) throw new InvalidRequestException("this email is not valid");
        if (user.getFirstName() == null || isBlank.test(user.getFirstName()))
            throw new InvalidRequestException("missing first name");
        if (user.getUsername() == null || isBlank.test(user.getUsername()))
            throw new InvalidRequestException("missing username");
        if (user.getLastName() == null || isBlank.test(user.getLastName()))
            throw new InvalidRequestException("missing last name");
        if (user.getDob() == null || isBlank.test(user.getDob().toString()))
            throw new InvalidRequestException("missing Data of Birth");
        if (!isAdult.test(user)) throw new InvalidRequestException("user is not of age");

        return true;
    }


    private static BiPredicate<UserDAO, User> isEmailTaken = (userDAO, user) -> {
        try {
            return userDAO.getUserByEmail(user.getEmail()) != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    };

    private static BiPredicate<UserDAO, User> isUsernameTaken = (userDAO, user) -> {
        try {
            return userDAO.getUserByUsername(user.getUsername()) != null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    };
    private static Predicate<User> isEmailValid = email -> Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
            .matcher(email.getEmail())
            .matches();

    private static Predicate<String> isBlank = name -> name.trim().equals("");

    private static Predicate<User> isAdult = user -> Period.between(user.getDob(), LocalDate.now()).getYears() >= 18;


}
