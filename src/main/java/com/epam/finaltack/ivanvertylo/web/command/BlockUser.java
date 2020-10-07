package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUser extends Command{
    private final UserService userService;

    public BlockUser() {
        userService = new UserServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = userService.findUserByLogin(request.getParameter(Constant.LOGIN));
        if (user.getId() != null && !user.getRole().equals(Constant.ROLE_ADMIN)){
            user.setBlocked(!user.getBlocked());
            userService.updateUser(user);
            return Path.CONTROLLER_ADMIN_PAGE;
        }
        return setErrorReturn(Path.CONTROLLER_ADMIN_PAGE,Constant.ERROR_ADMIN_BLOCKED_USER);
    }
}
