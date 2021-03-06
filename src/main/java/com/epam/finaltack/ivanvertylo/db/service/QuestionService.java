package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> findQuestionsByTestId(Integer testId);

    void saveQuestion(Question question);

    void updateQuestion(Question question);

    Question findQuestionById(Integer id);

    void deleteQuestion(Integer id);
}
