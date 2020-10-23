package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTestInfo extends Command {

    private final TestService testService;
    private static final Logger LOG = Logger.getLogger(UpdateTestInfo.class);

    public UpdateTestInfo(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Test test = testService.findTestById(Integer.parseInt(request.getParameter(Constant.TEST_ID)));
        test.setName(ServletUtil.getUTF8(request, Constant.TEST_NAME));
        test.setSubject(ServletUtil.getUTF8(request, Constant.TEST_SUBJECT));
        test.setTime(Integer.parseInt(request.getParameter(Constant.TEST_TIME)));
        test.setIsPublic(request.getParameter(Constant.TEST_PUBLIC) != null);
        testService.updateTest(test);
        LOG.info("Performing execute update test: " + test);
        return Path.CONTROLLER_EDITOR_PAGE + "?id=" + test.getId();
    }
}
