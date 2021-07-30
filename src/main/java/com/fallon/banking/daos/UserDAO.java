package com.fallon.banking.daos;


import com.fallon.banking.models.User;
import com.fallon.banking.util.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class UserDAO {
    public void save(User newUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
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
                System.out.println(newUser);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }




}
