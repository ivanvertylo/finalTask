package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestion extends Command {

    private final QuestionService questionService;

    private static final Logger LOG = Logger.getLogger(DeleteQuestion.class);

    public DeleteQuestion(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        questionService.deleteQuestion(Integer.parseInt(request.getParameter(Constant.QUESTION_ID)));
        LOG.info("Performing execute delete question id: " + request.getParameter(Constant.QUESTION_ID));
        return Path.CONTROLLER_EDITOR_PAGE + "?id=" + request.getParameter(Constant.TEST_ID);
    }
}
