package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestion extends Command {

    private final QuestionService questionService;
    private static final Logger LOG = Logger.getLogger(UpdateQuestion.class);

    public UpdateQuestion(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Question question = questionService.findQuestionById(Integer.parseInt(request.getParameter(Constant.QUESTION_ID)));
        question.setName(ServletUtil.getUTF8(request, Constant.QUESTION_NAME));
        for (int i = 0; i < question.getAnswers().size(); i++) {
            question.getAnswers().get(i).setName(ServletUtil.getUTF8(request, question.getAnswers().get(i).getId().toString()));
            question.getAnswers().get(i).setRight(request.getParameterValues(question.getAnswers().get(i).getId().toString()).length > 1);
        }
        questionService.updateQuestion(question);
        LOG.info("Performing execute update question: " + question);
        return Path.CONTROLLER_EDITOR_PAGE + "?id=" + question.getTestId();
    }
}
