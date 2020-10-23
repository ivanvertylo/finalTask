package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
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
public class DeleteQuestionTest {
    private static final String STR = "5";
    private DeleteQuestion deleteQuestion;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private QuestionService questionService;
    @Before
    public void before(){
        when(request.getParameter(Constant.QUESTION_ID)).thenReturn(STR);
        deleteQuestion = new DeleteQuestion(questionService);
    }
    @Test
    public void test(){
        deleteQuestion.execute(request,response);
    }
}
