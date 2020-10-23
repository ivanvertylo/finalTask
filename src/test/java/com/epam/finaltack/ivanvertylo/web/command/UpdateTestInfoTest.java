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
public class UpdateTestInfoTest {
    private static final String STR = "5";
    private UpdateTestInfo updateTestInfo;
    @Mock
    private com.epam.finaltack.ivanvertylo.db.entity.Test test;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private TestService testService;

    @Before
    public void before() {
        when(request.getParameter(Constant.TEST_NAME)).thenReturn(STR);
        when(request.getParameter(Constant.TEST_SUBJECT)).thenReturn(STR);
        when(request.getParameter(Constant.TEST_TIME)).thenReturn(STR);
        when(request.getParameter(Constant.TEST_PUBLIC)).thenReturn(STR);
        when(request.getParameter(Constant.TEST_ID)).thenReturn(STR);
        when(testService.findTestById(Integer.parseInt(STR))).thenReturn(test);
        updateTestInfo = new UpdateTestInfo(testService);
    }

    @Test
    public void Test() {
        updateTestInfo.execute(request, response);
        verify(testService).updateTest(test);
    }
}
