package com.fallon.banking.daos;

import com.fallon.banking.models.Account;
import com.fallon.banking.models.User;
import com.fallon.banking.util.ConnectionFactory;
import org.springframework.test.context.jdbc.Sql;

import java.sql.*;

public class AccountDAO {

    public void save(int id, Account account) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sqlInsertAccount = "insert into account_info(type, nickname) values(?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsertAccount, new String[]{"account_id"});
            pstmt.setString(1, account.getType());
            pstmt.setString(2, account.getNickName());

            int rowsInserted = pstmt.executeUpdate();





            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    account.setId(rs.getInt("account_id"));
                }
                System.out.println(account);
            }

            makeUserRelation(id, account.getId(), conn);


            if(account.getType() == "checking"){
                String makeAccountRelation = "insert into checking_accounts(account_id, balance) values(?,?)";
                PreparedStatement pstmt3 = conn.prepareStatement(makeAccountRelation);
                pstmt3.setInt(1, account.getId());
                pstmt3.setDouble(2,account.getBalance());
                pstmt3.executeUpdate();
            }
            if(account.getType() == "savings"){
                String makeAccountRelation = "insert into savings_accounts(account_id, balance) values(?,?)";
                PreparedStatement pstmt3 = conn.prepareStatement(makeAccountRelation);
                pstmt3.setInt(1, account.getId());
                pstmt3.setDouble(2,account.getBalance());
                pstmt3.executeUpdate();
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void makeUserRelation(int userId, int accountId, Connection conn) throws SQLException {
        String makeUserRelation ="insert into user_accounts(user_id, account_id) values(?,?)";
        PreparedStatement pstmt2 = conn.prepareStatement(makeUserRelation);
        pstmt2.setInt(1, userId);
        pstmt2.setInt(2,accountId);
        pstmt2.executeUpdate();
    }

}
