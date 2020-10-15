package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/editor/*")
public class EditorPage extends HttpServlet {

    TestService testService = new TestServiceImpl();
    QuestionService questionService = new QuestionServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Test test = testService.findTestById(Integer.parseInt(req.getParameter(Constant.ID)));
            HttpSession session = req.getSession();
            if (test.getId() != null && test.getAuthor().equals(session.getAttribute(Constant.LOGIN))) {
                List<Question> questions = questionService.findQuestionsByTestId(test.getId());
                req.setAttribute(Constant.TEST_ID, test.getId());
                req.setAttribute(Constant.TEST_NAME, test.getName());
                req.setAttribute(Constant.TEST_SUBJECT, test.getSubject());
                req.setAttribute(Constant.TEST_TIME, test.getTime());
                req.setAttribute(Constant.TEST_PUBLIC, test.getIsPublic());
                req.setAttribute("questions",questions);
                getServletContext().getRequestDispatcher(Path.EDITOR_PAGE).forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
        }
    }
}
