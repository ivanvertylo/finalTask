package com.epam.finaltack.ivanvertylo.web.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLocaleTest {
    private ChangeLocale changeLocale;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    private static final String locale = "DOFOD";
    @Before
    public void before(){
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("locale")).thenReturn(locale);
        changeLocale = new ChangeLocale();
    }
    @Test
    public void verifyExecute() throws IOException, ServletException {
        changeLocale.execute(request,response);
        verify(request).getParameter("locale");
        verify(session).setAttribute("currentLocale",locale);
    }
}
