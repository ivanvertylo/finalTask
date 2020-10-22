package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.Answer;

import java.util.List;

public interface AnswerRepository {
    List<Answer> findAnswersByQuestionId(Integer questionId);

    void saveAnswer(Answer answer);

    void updateAnswer(Answer answer);
}
