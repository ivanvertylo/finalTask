import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

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

    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
    }

}
