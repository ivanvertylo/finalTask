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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteTestTest {
    private static final String STR = "5";
    private DeleteTest deleteTest;
    @Mock
    private TestService testService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Before
    public void before(){
        when(request.getParameter(Constant.TEST_ID)).thenReturn(STR);
        deleteTest = new DeleteTest(testService);
    }
    @Test
    public void test(){
        deleteTest.execute(request,response);
        verify(testService).deleteTest(Integer.parseInt(STR));
    }
}
