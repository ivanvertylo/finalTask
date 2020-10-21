package com.epam.finaltack.ivanvertylo.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance(){
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager(){
        Context initContext;
        try {
            initContext = new InitialContext();
            ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/ST4DB");
        } catch (NamingException e) {
            LOG.error("DBManager "+e.getMessage());
        }
    }

    private DataSource ds;

    public Connection getConnection(){
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error("getConnection "+ex.getMessage());
        }
        return con;
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("rollback "+ex.getMessage());
            }
        }
    }

    public void close(Statement stmt, Connection con, ResultSet res) {
        if (stmt != null) {
            try {
                res.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                LOG.error("close "+ex.getMessage());
            }
        }
    }
    public void close(Statement stmt, Connection con) {
        if (stmt != null) {
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                LOG.error("close "+ex.getMessage());
            }
        }
    }

}
