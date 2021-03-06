package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> findQuestionsByTestId(Integer testId);

    int saveQuestion(Question question);

    void updateQuestion(Question question);

    Question findQuestionById(Integer questionId);

    void deleteQuestion(Integer id);
}
