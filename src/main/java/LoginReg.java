import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginReg extends Command{
    private static final Logger LOGGER = Logger.getLogger(LoginReg.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("role","user");
        httpSession.setAttribute("username","Иван Вертыло");
        return Path.MAIN_PAGE;
    }
}
