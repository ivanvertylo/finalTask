package com.epam.finaltack.ivanvertylo.web.command;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.Path;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;
import com.epam.finaltack.ivanvertylo.db.service.TestService;
import com.epam.finaltack.ivanvertylo.db.service.impl.QuestionServiceImpl;
import com.epam.finaltack.ivanvertylo.db.service.impl.TestServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class CheckTest extends Command {

    private final TestService testService = new TestServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();
    private static final Logger LOG = Logger.getLogger(CheckTest.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int testId = Integer.parseInt(request.getParameter(Constant.TEST_ID));
        LOG.info("Performing execute test id=" + testId);
        if (testService.getPoints((Integer) session.getAttribute(Constant.USER_ID), testId) != null && testService.getPoints((Integer) session.getAttribute(Constant.USER_ID), testId) != -1) {
            LOG.info("Test already passed");
            return Path.CONTROLLER_TEST_PAGE + "?id=" + testId;
        }
        String[] reqAnswers = request.getParameterValues("answers");
        List<String> answers = reqAnswers != null ? Arrays.asList(reqAnswers) : new ArrayList<>();
        int totalPoints = 0;
        int maxPoints = 0;
        List<Question> questions = questionService.findQuestionsByTestId(testId);
        for (Question question : questions) {
            maxPoints += question.getPoint();
            boolean flag = reqAnswers != null;
            for (Answer answer : question.getAnswers()) {
                if (answer.getRight()) {
                    if (!answers.contains(answer.getId().toString())) {
                        flag = false;
                        break;
                    }
                } else {
                    if (answers.contains(answer.getId().toString())) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                totalPoints += question.getPoint();
            }
        }
        testService.setPoints((Integer) session.getAttribute(Constant.USER_ID), testId, (totalPoints * 100) / maxPoints);
        LOG.info("Total points=" + totalPoints + " Max points=" + maxPoints);
        return Path.CONTROLLER_TEST_PAGE + "?id=" + testId;
    }
}
