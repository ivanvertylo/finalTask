package com.epam.finaltack.ivanvertylo.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //sce.getServletContext().setAttribute("locale","ru");
    }
}
