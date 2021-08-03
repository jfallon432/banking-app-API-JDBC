package com.fallon.banking.daos;


import com.fallon.banking.models.Account;
import com.fallon.banking.models.CheckingAccount;
import com.fallon.banking.models.SavingsAccount;
import com.fallon.banking.models.User;
import com.fallon.banking.util.ConnectionFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
public class UserDAO {
    Connection conn;

    @PostConstruct
    public void init(){
        System.out.println("init method called");
        conn = ConnectionFactory.getInstance().getConnection();
    }

    @PreDestroy
    public void destroy() throws SQLException {
        System.out.println("destroy method called");
        conn.close();
    }



    public void save(User newUser) throws SQLException {
        String sqlInsertUser = "insert into users(username, password, email, first_name, last_name, date_of_birth) values(?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sqlInsertUser, new String[]{"user_id"});
        pstmt.setString(1, newUser.getUsername());
        pstmt.setString(2, newUser.getPassword());
        pstmt.setString(3, newUser.getEmail());
        pstmt.setString(4, newUser.getFirstName());
        pstmt.setString(5, newUser.getLastName());
        pstmt.setDate(6, Date.valueOf(newUser.getDob()));
        int rowsInserted = pstmt.executeUpdate();

        if (rowsInserted != 0) {
            ResultSet rs = pstmt.getGeneratedKeys();

            while (rs.next()) {
                newUser.setId(rs.getInt("user_id"));
            }

        }

    }

    public User getUserByEmail(String email) throws SQLException {
        User user = null;
        PreparedStatement pstmt = conn.prepareStatement("select * from users where email = ?");
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth").toLocalDate());
        }
        return user;

    }

    public User getUserByUsername(String username) throws SQLException {
        User user = null;
        PreparedStatement pstmt = conn.prepareStatement("select * from users where username = ?");
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth").toLocalDate());
        }
        return user;
    }

    public User getUserByPassword(String password, String username) throws SQLException {
        User user = null;
        PreparedStatement pstmt = conn.prepareStatement("select * from users where password = ? and username = ?");
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            user = new User(rs.getInt("user_id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("date_of_birth").toLocalDate());
        }
        return user;
    }


    public Map getAccountsByUserId(int userId) {
        Account account = null;
        Map<Integer, Account> accounts = new HashMap<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql =
                    "select ai.account_id, ai.type, ai.nickname from user_accounts ua " +
                            "inner join users on ua.user_id = ?" +
                            "inner join account_info ai on ua.account_id = ai.account_id;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("type").equals("checking"))
                    account = new CheckingAccount();

                if (rs.getString("type").equals("savings"))
                    account = new SavingsAccount();
                account.setId(rs.getInt("account_id"));
                account.setNickName(rs.getString("nickname"));
                accounts.put(account.getId(), account);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }


}
