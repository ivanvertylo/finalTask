package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateQuestionTest {
    private static final String STR = "5";
    private UpdateQuestion updateQuestion;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private QuestionService questionService;
    @Mock
    Question question;
    @Before
    public void before(){
        when(request.getParameter(Constant.QUESTION_ID)).thenReturn(STR);
        when(request.getParameter(Constant.QUESTION_NAME)).thenReturn(STR);
        when(questionService.findQuestionById(Integer.parseInt(STR))).thenReturn(question);
        updateQuestion = new UpdateQuestion(questionService);
    }
    @Test
    public void test(){
        updateQuestion.execute(request,response);
    }
}
