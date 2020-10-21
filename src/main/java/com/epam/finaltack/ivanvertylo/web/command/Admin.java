package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Path;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin extends Command {
    private static final Logger LOG = Logger.getLogger(Admin.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Performing execute");
        return Path.CONTROLLER_ADMIN_PAGE;
    }
}
