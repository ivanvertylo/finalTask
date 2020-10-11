package com.epam.finaltack.ivanvertylo.web;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter(urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {

    private final Map<String, List<Object>> privateCommand = new TreeMap<>();
    private final Map<String, List<Object>> privateUrl = new TreeMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        privateCommand.put(Constant.COMMAND_ADMIN, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_CREATE_TEST, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_FIND_LOGIN, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_BLOCK_USER, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_UPDATE_TEST, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_SAVE_QUESTION, Arrays.asList(Constant.ROLE_ADMIN));
        privateCommand.put(Constant.COMMAND_UPDATE_QUESTION, Arrays.asList(Constant.ROLE_ADMIN));

        privateUrl.put(Path.CONTROLLER_ADMIN_PAGE, Arrays.asList(Constant.ROLE_ADMIN));
        privateUrl.put(Path.CONTROLLER_EDITOR_PAGE, Arrays.asList(Constant.ROLE_ADMIN));
        //privateUrl.put(Path.CONTROLLER_TEST_PAGE, Arrays.asList(Constant.ROLE_USER,Constant.ROLE_ADMIN));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url;
        try {
            url = ((HttpServletRequest) servletRequest).getRequestURI().split("/")[1];
        }
        catch (Exception e){
            url = Path.CONTROLLER_MAIN_PAGE;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();
        String commandName = servletRequest.getParameter("command");
        if (commandName != null){
            if(privateCommand.get(commandName) == null || privateCommand.get(commandName).contains(session.getAttribute(Constant.ROLE))){
                filterChain.doFilter(servletRequest,servletResponse);
            }
            else {
                ((HttpServletResponse)servletResponse).sendRedirect(Path.CONTROLLER_ERROR_PAGE);
            }
        }
        else {
            if (privateUrl.get(url) == null || privateUrl.get(url).contains(session.getAttribute(Constant.ROLE))){
                filterChain.doFilter(servletRequest,servletResponse);
            }
            else {
                ((HttpServletResponse)servletResponse).sendRedirect(Path.CONTROLLER_ERROR_PAGE);
            }
        }

    }

    @Override
    public void destroy() {

    }
}