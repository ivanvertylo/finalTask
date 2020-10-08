package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.ServletUtil;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveQuestion extends Command{

    private final QuestionService questionService;

    public SaveQuestion() {
        questionService = new QuestionServiceImpl();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Question question = new Question();
        String testId = request.getParameter(Constant.TEST_ID);
        question.setTestId(Integer.parseInt(testId));
        question.setPoint(Integer.parseInt(request.getParameter(Constant.QUESTION_POINT)));
        question.setName(ServletUtil.getUTF8(request,Constant.QUESTION_NAME));
        String[] answers = ServletUtil.getUTF8Array(request, Constant.ANSWER);
        String[] answersRight = request.getParameterValues("answerRight");
        List<Answer> listAnswers = new ArrayList<>();
        for (int i = 0; i < answers.length; i++){
            Answer answer = new Answer();
            answer.setName(answers[i]);
            answer.setRight(Boolean.parseBoolean(answersRight[i]));
            listAnswers.add(answer);
        }
        question.setAnswers(listAnswers);
        questionService.saveQuestion(question);
        return Path.CONTROLLER_EDITOR_PAGE+"?id="+testId;
    }
}
