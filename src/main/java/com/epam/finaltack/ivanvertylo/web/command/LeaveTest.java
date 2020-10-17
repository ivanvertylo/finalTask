package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LeaveTest extends Command{

    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        testService.setPoints((Integer)session.getAttribute(Constant.USER_ID),Integer.parseInt(request.getParameter(Constant.TEST_ID)),0);
        return Path.NO_REDIRECT;
    }
}
