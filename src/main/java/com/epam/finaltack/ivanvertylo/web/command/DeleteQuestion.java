package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteQuestion extends Command{

    private final QuestionService questionService = new QuestionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        questionService.deleteQuestion(Integer.parseInt(request.getParameter(Constant.QUESTION_ID)));
        return Path.CONTROLLER_EDITOR_PAGE+"?id="+request.getParameter(Constant.TEST_ID);
    }
}
