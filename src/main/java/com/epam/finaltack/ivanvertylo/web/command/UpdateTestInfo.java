package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTestInfo extends Command{

    private final TestService testService;

    public UpdateTestInfo() {
        testService = new TestServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Test test = testService.findTestById(Integer.parseInt(request.getParameter(Constant.TEST_ID)));
        test.setName(ServletUtil.getUTF8(request,Constant.TEST_NAME));
        test.setSubject(ServletUtil.getUTF8(request,Constant.TEST_SUBJECT));
        test.setTime(Integer.parseInt(request.getParameter(Constant.TEST_TIME)));
        test.setPublic(request.getParameter(Constant.TEST_PUBLIC) != null);
        testService.updateTest(test);
        return Path.CONTROLLER_EDITOR_PAGE+"?id="+test.getId();
    }
}
