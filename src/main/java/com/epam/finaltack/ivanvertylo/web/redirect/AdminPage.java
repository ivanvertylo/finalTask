package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
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
import java.util.List;

@WebServlet("/admin")
public class AdminPage extends HttpServlet {

    private final TestService testService = new TestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Test> tests = testService.findTestsByAuthor(session.getAttribute(Constant.LOGIN).toString());
        req.setAttribute("tests",tests);
        getServletContext().getRequestDispatcher(Path.ADMIN_PAGE).forward(req, resp);
    }
}
