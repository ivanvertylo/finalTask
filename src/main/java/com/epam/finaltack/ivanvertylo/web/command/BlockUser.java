package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUser extends Command{
    private final UserService userService;
    private static final Logger LOG = Logger.getLogger(BlockUser.class);

    public BlockUser(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.findUserByLogin(request.getParameter(Constant.LOGIN));
        LOG.info("Performing execute user id="+user.getId());
        if (user.getId() != null && !user.getRole().equals(Constant.ROLE_ADMIN)){
            user.setBlocked(!user.getBlocked());
            userService.updateUser(user);
            return Path.CONTROLLER_ADMIN_PAGE;
        }
        return setErrorReturn(Path.CONTROLLER_ADMIN_PAGE,Constant.ERROR_ADMIN_BLOCKED_USER);
    }
}
