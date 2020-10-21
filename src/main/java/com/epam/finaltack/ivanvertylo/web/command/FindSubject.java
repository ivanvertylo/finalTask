package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindSubject extends Command {
    private static final Logger LOG = Logger.getLogger(FindSubject.class);
    private final TestService testService = new TestServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        PrintWriter printWriter = response.getWriter();
        printWriter.write(gson.toJson(testService.findSubjectsCounts(request.getParameter("subject"))));
        printWriter.flush();
        LOG.info("Performing execute");
        return Path.NO_REDIRECT;
    }
}
