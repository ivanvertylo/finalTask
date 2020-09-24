package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginReg extends Command {

    private static final Logger LOGGER = Logger.getLogger(LoginReg.class);

    private final UserService userService;

    public LoginReg() {
        userService = new UserServiceImpl();
    }



    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (request.getParameter(Constant.IS_NEW_USER) == null) {
            User user = userService.validateUser(new User(request.getParameter(Constant.LOGIN),
                    request.getParameter(Constant.PASSWORD)));
            if (user != null) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(Constant.ROLE, user.getRole());
                httpSession.setAttribute(Constant.USERNAME, user.getUsername());
            } else {
                request.setAttribute(Constant.ERROR_LOGIN, true);
            }
        } else {
            String login = request.getParameter(Constant.LOGIN);
            String password = request.getParameter(Constant.PASSWORD);

            if (userService.registerUser(new User(login, password)) == 0) {
                request.setAttribute(Constant.ERROR_REGISTER, true);
            }
            else {
                User user = userService.validateUser(new User(login,
                        password));
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute(Constant.ROLE, user.getRole());
                httpSession.setAttribute(Constant.USERNAME, user.getUsername());
            }
        }
        return Path.MAIN_PAGE;
    }
}
