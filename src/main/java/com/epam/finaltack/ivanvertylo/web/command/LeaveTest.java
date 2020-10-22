package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LeaveTest extends Command {
    private static final Logger LOG = Logger.getLogger(LeaveTest.class);
    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        testService.setPoints((Integer) session.getAttribute(Constant.USER_ID), Integer.parseInt(request.getParameter(Constant.TEST_ID)), 0);
        LOG.info("Performing execute");
        return Path.NO_REDIRECT;
    }
}
