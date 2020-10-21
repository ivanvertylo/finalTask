package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePoints extends Command{

    private final TestService testService = new TestServiceImpl();
    private static final Logger LOG = Logger.getLogger(DeletePoints.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (testService.getPoints(Integer.parseInt(request.getParameter(Constant.USER_ID)), Integer.parseInt(request.getParameter(Constant.TEST_ID))) == -1) {
            testService.setPoints(Integer.parseInt(request.getParameter(Constant.USER_ID)), Integer.parseInt(request.getParameter(Constant.TEST_ID)), 0);
            LOG.info("Performing execute set points fot test id: "+request.getParameter(Constant.TEST_ID)+" points=0");
        } else {
            testService.setPoints(Integer.parseInt(request.getParameter(Constant.USER_ID)), Integer.parseInt(request.getParameter(Constant.TEST_ID)), -1);
            LOG.info("Performing execute set points fot test id: "+request.getParameter(Constant.TEST_ID)+" points=-1");
        }
        return Path.CONTROLLER_PROFILE_PAGE+"?user="+request.getParameter(Constant.USER_LOGIN);
    }
}
