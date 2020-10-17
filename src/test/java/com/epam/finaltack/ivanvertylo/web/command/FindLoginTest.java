package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindLoginTest {
    private static final String LOGIN = "i654654";

    private FindLogin findLogin;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private PrintWriter printWriter;

    @Before
    public void before() throws IOException {
        User user = new User();
        user.setLogin(LOGIN);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getParameter(Constant.LOGIN)).thenReturn(LOGIN);
        when(userService.findUserByLogin(LOGIN)).thenReturn(user);
        findLogin = new FindLogin(userService);
    }

    @Test
    public void testExecute() throws IOException, ServletException {
        String result = findLogin.execute(request,response);
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(userService).findUserByLogin(LOGIN);
        verify(request).getParameter(Constant.LOGIN);
        verify(printWriter).flush();
        assertEquals(result, Path.NO_REDIRECT);
    }
}
