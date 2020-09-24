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
    private static final Logger LOGGER = Logger.getLogger(DBManager.class);

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
            LOGGER.error(e.getMessage());
        }
    }

    private DataSource ds;

    public Connection getConnection(){
        Connection con = null;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }
        return con;
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

    public void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

}
