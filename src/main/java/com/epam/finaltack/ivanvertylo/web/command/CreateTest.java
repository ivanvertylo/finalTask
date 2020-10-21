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
import javax.servlet.http.HttpSession;

public class CreateTest extends Command {

    private final TestService testService;
    private static final Logger LOG = Logger.getLogger(CreateTest.class);

    public CreateTest() {
        this.testService = new TestServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Test test = new Test();
        test.setName(ServletUtil.getUTF8(request, Constant.TEST_NAME));
        test.setAuthor((String) session.getAttribute(Constant.LOGIN));
        test.setIsPublic(false);
        int testId = testService.save(test);
        LOG.info("Performing execute test="+test);
        return Path.CONTROLLER_EDITOR_PAGE + "?" + Constant.ID + "=" + testId;
    }
}
