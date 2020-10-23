package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteTest extends Command {
    private static final Logger LOG = Logger.getLogger(DeleteTest.class);
    private final TestService testService;

    public DeleteTest(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("Performing execute");
        testService.deleteTest(Integer.parseInt(request.getParameter(Constant.TEST_ID)));
        return Path.CONTROLLER_ADMIN_PAGE;
    }
}
