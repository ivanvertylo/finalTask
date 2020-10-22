package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/test")
public class TestPage extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(TestPage.class);
    private final TestService testService = new TestServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("/test");
        try {
            Test test = testService.findTestById(Integer.parseInt(req.getParameter(Constant.ID)));
            HttpSession session = req.getSession();
            if (test.getId() != null && test.getIsPublic()) {
                List<Question> questions = questionService.findQuestionsByTestId(test.getId());
                req.setAttribute(Constant.TEST_ID, test.getId());
                req.setAttribute(Constant.TEST_NAME, test.getName());
                req.setAttribute(Constant.TEST_TIME, test.getTime());
                Integer total = testService.getPoints((Integer) session.getAttribute("userId"), test.getId());
                if (total == null || total == -1) {
                    req.setAttribute("questions", questions);
                } else {
                    req.setAttribute("total", total);
                }
                getServletContext().getRequestDispatcher(Path.TEST_PAGE).forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
        }
    }
}
