package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaveTestTest {
    private static final String TEST_ID = "5";
    private static final Object USER_ID = 5;
    private LeaveTest leaveTest;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TestService testService;

    @Before
    public void before(){
        when (request.getSession()).thenReturn(session);
        when(request.getParameter(Constant.TEST_ID)).thenReturn(TEST_ID);
        when(session.getAttribute(Constant.USER_ID)).thenReturn(USER_ID);
        leaveTest = new LeaveTest(testService);
    }
    @Test
    public void Test(){
        leaveTest.execute(request,response);
        verify(testService).setPoints((Integer)USER_ID,Integer.parseInt(TEST_ID),0);
    }
}
