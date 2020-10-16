package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletePoints extends Command{

    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        switch (testService.getPoints(Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("testId")))){
            case -1:
                testService.setPoints(Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("testId")),0);
                break;
            default:
                testService.setPoints(Integer.parseInt(request.getParameter("userId")),Integer.parseInt(request.getParameter("testId")),-1);
                break;
        }
        return Path.CONTROLLER_PROFILE_PAGE+"?user="+request.getParameter("userLogin");
    }
}
