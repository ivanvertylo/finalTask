package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.Test;

public interface TestRepository {
    int save(Test test);
    Test findTestById(Integer id);
}
