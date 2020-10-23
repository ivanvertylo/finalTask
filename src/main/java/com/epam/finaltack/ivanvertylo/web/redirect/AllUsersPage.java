package com.epam.finaltack.ivanvertylo.web.redirect;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import com.epam.finaltack.ivanvertylo.db.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allUsers")
public class AllUsersPage extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsersTestCount> usersTestCounts = userService.findAllUsers();
        req.setAttribute("usersTestCounts",usersTestCounts);
        getServletContext().getRequestDispatcher(Path.USERS_PAGE).forward(req, resp);
    }
}
