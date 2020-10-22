package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeUsername extends Command {
    private final UserService userService = new UserServiceImpl();
    private static final Logger LOG = Logger.getLogger(ChangeUsername.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = userService.findUserByLogin(session.getAttribute(Constant.LOGIN).toString());
        if (user.getRole().equals(Constant.ROLE_ADMIN)) {
            user = userService.findUserByLogin(request.getParameter(Constant.USER));

        }
        user.setUsername(ServletUtil.getUTF8(request, Constant.USERNAME));
        userService.updateUser(user);
        LOG.info("Performing execute user id=" + user.getId() + " username changed to " + user.getUsername());
        if (user.getLogin().equals(session.getAttribute(Constant.LOGIN))) {
            session.setAttribute(Constant.USERNAME, user.getUsername());
        }
        return Path.CONTROLLER_PROFILE_PAGE + "?user=" + user.getLogin();
    }
}
