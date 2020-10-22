package com.epam.finaltack.ivanvertylo.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {
    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException, ServletException;

    public final String setErrorReturn(String path, String errorName) {
        return !errorName.equals("") ? path + "?" + errorName + "=true" : path;
    }

    @Override
    public final String toString() {
        return getClass().getSimpleName();
    }
}
