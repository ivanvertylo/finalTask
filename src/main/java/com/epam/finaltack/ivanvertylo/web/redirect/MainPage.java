package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/main")
public class MainPage extends HttpServlet {

    private final TestService testService = new TestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("sort") == null) {
            setSort(5, testService.findAllTestNameSort("",5, "asc", 0), 0, "asc","name","", req);
        } else {
            switch (req.getParameter("sort")) {
                case "name":
                    setSort(Integer.parseInt(req.getParameter("pagination")),
                            testService.findAllTestNameSort(req.getParameter("subject"),Integer.parseInt(req.getParameter("pagination")),
                                    req.getParameter("type"), req.getParameter("offset").equals("") ? 0
                                            : Integer.parseInt(req.getParameter("offset"))),
                            req.getParameter("offset").equals("") ? 0 : Integer.parseInt(req.getParameter("offset")),
                            req.getParameter("type"),
                            req.getParameter("sort"),
                            req.getParameter("subject"), req);
                    break;
                case "question":
                    setSort(Integer.parseInt(req.getParameter("pagination")),
                            testService.findAllTestByQuestionSort(req.getParameter("subject"),Integer.parseInt(req.getParameter("pagination")),
                                    req.getParameter("type"), req.getParameter("offset").equals("") ? 0
                                            : Integer.parseInt(req.getParameter("offset"))),
                            req.getParameter("offset").equals("") ? 0 : Integer.parseInt(req.getParameter("offset")),
                            req.getParameter("type"),
                            req.getParameter("sort"),
                            req.getParameter("subject"), req);
                    break;
                case "hard":
                    setSort(Integer.parseInt(req.getParameter("pagination")),
                            testService.findTestsByComplexitySort(req.getParameter("subject"),Integer.parseInt(req.getParameter("pagination")),
                                    req.getParameter("type"), req.getParameter("offset").equals("") ? 0
                                            : Integer.parseInt(req.getParameter("offset"))),
                            req.getParameter("offset").equals("") ? 0 : Integer.parseInt(req.getParameter("offset")),
                            req.getParameter("type"),
                            req.getParameter("sort"),
                            req.getParameter("subject"), req);
                    break;
            }
        }
        getServletContext().getRequestDispatcher(Path.MAIN_PAGE).forward(req, resp);
    }

    private void setSort(Integer pagination, List<Test> tests, Integer offset, String type,String sort,String subject, HttpServletRequest req) {
        req.setAttribute("pagination", pagination);
        req.setAttribute("pages", (int) Math.ceil(((double) testService.getAllTestsCount()) / pagination));
        req.setAttribute("tests", tests);
        req.setAttribute("type", type);
        req.setAttribute("offset", offset);
        req.setAttribute("sort", sort);
        req.setAttribute("subject", subject);
    }
}
