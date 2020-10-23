package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount;
import com.epam.finaltack.ivanvertylo.db.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOG = Logger.getLogger(UserRepositoryImpl.class);

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
            LOG.info("Performing findUserByLogin " + requests);
        } catch (Exception e) {
            LOG.error("Performing findUserByLogin");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
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
            prst.setBoolean(5, false);
            res = prst.executeUpdate();
            con.commit();
            LOG.info("Performing saveUser " + res);
        } catch (Exception e) {
            LOG.error("Performing saveUser");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con);
        }
        return res;
    }

    @Override
    public void updateUser(User user) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_UPDATE_USER);
            prst.setString(1, user.getLogin());
            prst.setString(2, user.getUsername());
            prst.setString(3, user.getRole());
            prst.setBoolean(4, user.getBlocked());
            prst.setString(5, user.getPassword());
            prst.setInt(6, user.getId());
            prst.executeUpdate();
            con.commit();
            LOG.info("Performing updateUser");
        } catch (Exception e) {
            LOG.error("Performing updateUser");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con);
        }
    }

    @Override
    public List<UsersTestCount> findAllUsers() {
        List<UsersTestCount> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            rs = st.executeQuery(Query.SQL_FIND_ALL_USERS);
            while (rs.next()) {
                requests.add(extractUsersCount(rs));
            }
            con.commit();
            LOG.info("Performing findAllUsers " + requests);
        } catch (Exception e) {
            LOG.error("Performing findAllUsers");
            dbManager.rollback(con);
        } finally {
            dbManager.close(st, con, rs);
        }
        return requests;
    }

    @Override
    public int getCountTestForUser(String login) {
        int requests = 0;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_GET_COUNT_TEST_FOR_USER);
            prst.setString(1, login);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests = rs.getInt("count(user_test.test_id)");
            }
            con.commit();
            LOG.info("Performing findUserByLogin " + requests);
        } catch (Exception e) {
            LOG.error("Performing findUserByLogin");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    private UsersTestCount extractUsersCount(ResultSet rs) throws SQLException {
        UsersTestCount usersTestCount = new UsersTestCount();
        usersTestCount.setUser(extractUser(rs));
        return usersTestCount;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Query.ID));
        user.setLogin(rs.getString(Query.USER_LOGIN));
        user.setPassword(rs.getString(Query.USER_PASSWORD));
        user.setRole(rs.getString(Query.USER_ROLE));
        user.setUsername(rs.getString(Query.USER_USERNAME));
        user.setBlocked(rs.getBoolean(Query.USER_BLOCKED));
        return user;
    }
}
