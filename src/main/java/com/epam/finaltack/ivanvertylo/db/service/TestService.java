package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.Test;

public interface TestService {
    int save(Test test);
    Test findTestById(Integer id);
}
