package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
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
public class ChangeUsernameTest {

    private static final String LOGIN = "login";
    private static final String USERNAME = "username";
    private ChangeUsername changeUsername;
    private User user;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void before() {
        user = new User();
        user.setLogin(LOGIN);
        user.setRole(Constant.ROLE_USER);
        when(session.getAttribute(Constant.LOGIN)).thenReturn(LOGIN);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter(Constant.USERNAME)).thenReturn(USERNAME);
        when(userService.findUserByLogin(LOGIN)).thenReturn(user);
        changeUsername = new ChangeUsername(userService);
    }

    @Test
    public void verifyExecute(){
        String result = changeUsername.execute(request, response);
        verify(userService).updateUser(user);
        assertEquals(result, Path.CONTROLLER_PROFILE_PAGE + "?user=" + user.getLogin());
    }

    @Test
    public void Test() {
        user.setRole(Constant.ROLE_ADMIN);
        when(request.getParameter(Constant.USER)).thenReturn(LOGIN);
        String result = changeUsername.execute(request, response);
        verify(userService).updateUser(user);
        assertEquals(result, Path.CONTROLLER_PROFILE_PAGE + "?user=" + user.getLogin());
    }
}
