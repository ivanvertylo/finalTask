package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLocale extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeLocale.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        session.setAttribute("currentLocale", locale);
        LOG.info("Performing execute locale changed to " + session.getAttribute("currentLocale"));
        return Path.CONTROLLER_MAIN_PAGE;
    }
}
