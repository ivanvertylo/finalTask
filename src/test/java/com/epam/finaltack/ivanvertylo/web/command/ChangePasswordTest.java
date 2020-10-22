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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordTest {
    private static final String LOGIN = "ivan";
    private ChangePassword changePassword;
    @Mock
    private User user;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Before
    public void before(){
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(Constant.LOGIN)).thenReturn(LOGIN);
        when(userService.findUserByLogin(LOGIN)).thenReturn(user);
        changePassword = new ChangePassword(userService);
    }
    @Test
    public void verifyExecute(){
        String result = changePassword.execute(request,response);
        verify(user).setPassword(request.getParameter(Constant.PASSWORD));
        verify(userService).updateUser(user);
        verify(session).invalidate();
        assertEquals(result,Path.CONTROLLER_MAIN_PAGE);
    }
}
