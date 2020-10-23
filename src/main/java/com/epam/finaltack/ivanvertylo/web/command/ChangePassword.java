package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends Command {

    private final UserService userService;
    private static final Logger LOG = Logger.getLogger(ChangePassword.class);

    public ChangePassword(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = userService.findUserByLogin(session.getAttribute(Constant.LOGIN).toString());
        user.setPassword(DigestUtils.md5Hex(request.getParameter(Constant.PASSWORD)));
        userService.updateUser(user);
        session.invalidate();
        LOG.info("Performing execute user id=" + user.getId());
        return Path.CONTROLLER_MAIN_PAGE;
    }
}
