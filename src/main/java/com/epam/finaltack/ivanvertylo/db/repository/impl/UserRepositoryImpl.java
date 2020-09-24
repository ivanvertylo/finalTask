package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
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
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_USER_BY_LOGIN);
            prst.setString(1, login);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests = extractUser(rs);
            }
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
            LOGGER.error(e.getMessage());
        } finally {
            dbManager.close(prst,con,rs);
        }
        return requests;
    }

    @Override
    public int saveUser(User user) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        int res = 0;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_SAVE_USER);
            prst.setString(1, user.getLogin());
            prst.setString(2, DigestUtils.md5Hex(user.getPassword()));
            prst.setString(3, user.getUsername());
            prst.setString(4, Constant.ROLE_USER);
            res = prst.executeUpdate();
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
            LOGGER.error(e.getMessage());
        } finally {
            dbManager.close(prst,con);
        }
        return res;
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
