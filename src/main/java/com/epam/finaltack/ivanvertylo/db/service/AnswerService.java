package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findAnswersByQuestionsId(Integer questionId);
}
