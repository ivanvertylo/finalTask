package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class CheckTest extends Command{

    private final TestService testService = new TestServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int testId = Integer.parseInt(request.getParameter(Constant.TEST_ID));
        if (testService.getPoints((Integer)session.getAttribute("userId"),testId) != null){
            return Path.CONTROLLER_TEST_PAGE+"?id="+testId;
        }
        String [] reqAnswers = request.getParameterValues("answers");
        List<String> answers = reqAnswers != null ? Arrays.asList(reqAnswers) : new ArrayList<>();
        int totalPoints = 0;
        int maxPoints = 0;
        List<Question> questions = questionService.findQuestionsByTestId(testId);
        for (Question question : questions){
            maxPoints += question.getPoint();
            boolean flag = reqAnswers != null;
            for (Answer answer : question.getAnswers()){
                if(answer.getRight()){
                    if (!answers.contains(answer.getId().toString())) {
                        flag = false;
                        break;
                    }
                }
                else {
                    if (answers.contains(answer.getId().toString())) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag){
                totalPoints += question.getPoint();
            }
        }
        testService.setPoints((Integer) session.getAttribute("userId"),testId,(totalPoints*100)/maxPoints);
        return Path.CONTROLLER_TEST_PAGE+"?id="+testId;
    }
}
