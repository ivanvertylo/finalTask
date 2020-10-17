package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangePassword extends Command{

    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = userService.findUserByLogin(session.getAttribute(Constant.LOGIN).toString());
        user.setPassword(request.getParameter("password"));
        userService.updateUser(user);
        session.invalidate();
        return Path.CONTROLLER_MAIN_PAGE;
    }
}
