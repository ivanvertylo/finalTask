package com.epam.finaltack.ivanvertylo.web;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.web.command.Command;
import com.epam.finaltack.ivanvertylo.web.command.CommandContainer;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.info("Controller process");
        String commandName = request.getParameter("command");
        CommandContainer commandContainer = new CommandContainer();
        Command command = commandContainer.get(commandName);
        if (command != null) {
            String redirect = command.execute(request, response);
            if (!redirect.equals(Path.NO_REDIRECT)) {
                response.sendRedirect(redirect);
            }
        } else {
            response.sendRedirect(Path.CONTROLLER_ERROR_PAGE);
        }
    }
}
