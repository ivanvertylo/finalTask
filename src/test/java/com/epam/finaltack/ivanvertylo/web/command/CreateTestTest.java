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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateTestTest {
    private static final String STR = "";
    private CreateTest createTest;
    private com.epam.finaltack.ivanvertylo.db.entity.Test test;
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
        when (request.getParameter(Constant.TEST_NAME)).thenReturn(STR);
        when (session.getAttribute(Constant.LOGIN)).thenReturn(STR);
        createTest = new CreateTest(testService);
    }
    @Test
    public void test(){
        createTest.execute(request,response);
    }
}
