package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.Test;

import java.util.List;

public interface TestService {
    int save(Test test);
    Test findTestById(Integer id);
    List<Test> findTestsByAuthor(String author);
}
