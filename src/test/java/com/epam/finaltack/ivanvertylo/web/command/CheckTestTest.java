package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckTestTest {
    private static final Integer TEST_ID = 5;
    private static final Integer USER_ID = 5;
    private static final Integer POINTS = 5;
    private static final String REQUEST_STRING_TEST_ID = "5";
    private CheckTest checkTest;
    @Mock
    List<Question> questions;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TestService testService;
    @Mock
    private QuestionService questionService;
    @Mock
    private HttpSession session;

    @Before
    public void before(){
        when (request.getSession()).thenReturn(session);
        when(request.getParameter(Constant.TEST_ID)).thenReturn(REQUEST_STRING_TEST_ID);
        when((Integer) session.getAttribute(Constant.USER_ID)).thenReturn(USER_ID);
        when(testService.getPoints(USER_ID, TEST_ID)).thenReturn(POINTS);
        checkTest = new CheckTest(testService, questionService);
    }
    @Test
    public void Test(){
        checkTest.execute(request,response);
    }
}
