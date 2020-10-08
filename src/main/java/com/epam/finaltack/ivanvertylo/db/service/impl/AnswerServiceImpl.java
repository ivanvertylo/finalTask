package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.repository.AnswerRepository;
import com.epam.finaltack.ivanvertylo.db.repository.impl.AnswerRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.service.AnswerService;

import java.util.List;

public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl() {
        answerRepository = new AnswerRepositoryImpl();
    }

    @Override
    public List<Answer> findAnswersByQuestionsId(Integer questionId) {
        return answerRepository.findAnswersByQuestionId(questionId);
    }
}
