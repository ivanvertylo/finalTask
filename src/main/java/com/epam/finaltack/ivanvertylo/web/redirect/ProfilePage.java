package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.TestPoints;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfilePage extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(ProfilePage.class);
    private final UserService userService = new UserServiceImpl();
    private final TestService testService = new TestServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("/profile");
        User user = userService.findUserByLogin(req.getParameter("user"));
        if (user.getRole() == null) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
            return;
        }
        List<TestPoints> testPoints = testService.findTestPointsByUserLogin(user.getLogin());
        req.setAttribute("user", user);
        req.setAttribute("testPoints", testPoints);
        getServletContext().getRequestDispatcher(Path.PROFILE_PAGE).forward(req, resp);
    }
}
