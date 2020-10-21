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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BlockUserTest {
    private static final String LOGIN = "i654654";
    private static final Integer ID = 123;
    private static final String ROLE = "123";
    private BlockUser blockUser;
    private User user;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void before(){
        user = new User();
        user.setLogin(LOGIN);
        when(request.getParameter(Constant.LOGIN)).thenReturn(LOGIN);
        when(userService.findUserByLogin(LOGIN)).thenReturn(user);
        blockUser = new BlockUser(userService);
    }
    @Test
    public void verifyExecute() throws IOException, ServletException {
        blockUser.execute(request,response);
        verify(userService).findUserByLogin(LOGIN);
        verify(request).getParameter(Constant.LOGIN);
    }
    @Test
    public void testExecute() throws IOException, ServletException {
        user.setId(ID);
        user.setRole(ROLE);
        user.setBlocked(false);
        String result = blockUser.execute(request,response);
        assertEquals(result, Path.CONTROLLER_ADMIN_PAGE);
    }
    @Test
    public void testExecute2() throws IOException, ServletException {
        String result = blockUser.execute(request,response);
        assertEquals(result, blockUser.setErrorReturn(Path.CONTROLLER_ADMIN_PAGE,Constant.ERROR_ADMIN_BLOCKED_USER));
    }
}
