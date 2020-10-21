package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindLogin extends Command {
    private final UserService userService;
    private static final Logger LOG = Logger.getLogger(FindLogin.class);

    public FindLogin(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(gson.toJson(userService.findUserByLogin(request.getParameter(Constant.LOGIN))));
        printWriter.flush();
        LOG.info("Performing execute");
        return Path.NO_REDIRECT;
    }
}
