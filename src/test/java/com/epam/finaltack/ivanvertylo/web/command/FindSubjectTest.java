package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindSubjectTest {
    private static final String SUBJECT = "i654654";

    private FindSubject findSubject;
    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void before() throws IOException {
        User user = new User();
        user.setLogin(SUBJECT);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getParameter("subject")).thenReturn(SUBJECT);
        when(testService.findSubjectsCounts(SUBJECT)).thenReturn(new ArrayList<>());
        findSubject = new FindSubject(testService);
    }

    @Test
    public void testExecute() throws IOException, ServletException {
        String result = findSubject.execute(request,response);
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(printWriter).flush();
        assertEquals(result, Path.NO_REDIRECT);
    }
}
