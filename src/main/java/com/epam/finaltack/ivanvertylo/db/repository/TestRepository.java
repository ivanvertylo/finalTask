package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.Test;

import java.util.List;

public interface TestRepository {
    int save(Test test);
    Test findTestById(Integer id);
    List<Test> findTestsByAuthor(String author);
}
