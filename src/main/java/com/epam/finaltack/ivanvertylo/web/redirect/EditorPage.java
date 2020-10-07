package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editor/*")
public class EditorPage extends HttpServlet {

    TestService testService = new TestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            Test test = testService.findTestById(Integer.parseInt(req.getParameter(Constant.ID)));
            if (test.getId() != null && test.getAuthor().equals(session.getAttribute(Constant.LOGIN))) {
                req.setAttribute(Constant.TEST_NAME, test.getName());
                getServletContext().getRequestDispatcher(Path.EDITOR_PAGE).forward(req, resp);
            } else {
                getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
            }
        } catch (Exception e) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
        }
    }
}
