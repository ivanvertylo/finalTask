package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindTestByName extends Command {
    private static final Logger LOG = Logger.getLogger(FindTestByName.class);
    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        String result = request.getParameter(Constant.TEST_ID);
        String testName = request.getParameter(Constant.TEST_NAME);
        if (result != null) {
            Test test = testService.findTestById(Integer.parseInt(result));
            if (test.getName().equals(testName)) {
                printWriter.write(gson.toJson(false));
            } else {
                printWriter.write(gson.toJson(testService.findTestByName(testName)));
            }
        } else {
            printWriter.write(gson.toJson(testService.findTestByName(testName)));
        }
        printWriter.flush();
        LOG.info("Performing execute");
        return Path.NO_REDIRECT;
    }
}
