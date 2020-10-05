package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateTest extends Command {

    private final TestService testService;

    public CreateTest() {
        this.testService = new TestServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Test test = new Test();
        test.setName(ServletUtil.getUTF8(request, Constant.TEST_NAME));
        test.setAuthor((String) session.getAttribute(Constant.LOGIN));
        test.setPublic(false);
        int testId = testService.save(test);
        return Path.CONTROLLER_EDITOR_PAGE + "?" + Query.ID + "=" + testId;
    }
}
