import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class LoginReg extends Command{
    private static final Logger LOGGER = Logger.getLogger(LoginReg.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Connection connection = DBManager.getInstance().getConnection();
        return null;
    }
}
