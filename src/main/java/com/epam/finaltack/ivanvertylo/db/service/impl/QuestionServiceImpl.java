package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.repository.AnswerRepository;
import com.epam.finaltack.ivanvertylo.db.repository.QuestionRepository;
import com.epam.finaltack.ivanvertylo.db.repository.impl.AnswerRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.repository.impl.QuestionRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl() {
        questionRepository = new QuestionRepositoryImpl();
        answerRepository = new AnswerRepositoryImpl();
    }

    @Override
    public List<Question> findQuestionsByTestId(Integer testId) {
        return questionRepository.findQuestionsByTestId(testId);
    }

    @Override
    public void saveQuestion(Question question) {
        question.setId(questionRepository.saveQuestion(question));
        for (Answer answer : question.getAnswers()) {
            answer.setQuestionId(question.getId());
            answerRepository.saveAnswer(answer);
        }
    }

    @Override
    public void updateQuestion(Question question) {
        questionRepository.updateQuestion(question);
        for (Answer answer : question.getAnswers()){
            answerRepository.updateAnswer(answer);
        }
    }

    @Override
    public Question findQuestionById(Integer id) {
        return questionRepository.findQuestionById(id);
    }
}
