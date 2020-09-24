package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.SQL;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.repository.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);
    @Override
    public User findUserByLogin(String login) {
        User requests = new User();
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement pstm = con.prepareStatement(SQL.SQL_FIND_USER_BY_LOGIN);
            pstm.setString(1, login);
            rs = pstm.executeQuery();
            while (rs.next()) {
                requests = extractUser(rs);
            }
            con.commit();

        } catch (Exception e) {
            DBManager.getInstance().rollback(con);
            LOGGER.error(e.getMessage());
        } finally {
            DBManager.getInstance().close(rs);
            DBManager.getInstance().close(con);
        }
        return requests;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setUsername(rs.getString("username"));
        return user;
    }
}
