import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginReg extends Command {
    private static final Logger LOGGER = Logger.getLogger(LoginReg.class);
    UserService userService;

    public LoginReg() {
        userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = userService.validateUser(request.getParameter("login"),request.getParameter("password"));
        if (user != null){
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("role",user.getRole());
            httpSession.setAttribute("username",user.getUsername());
        }
        else request.setAttribute("loginError",true);
        return Path.MAIN_PAGE;
    }
}
