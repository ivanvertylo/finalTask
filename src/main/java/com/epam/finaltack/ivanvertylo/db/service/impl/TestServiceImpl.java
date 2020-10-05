package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.repository.TestRepository;
import com.epam.finaltack.ivanvertylo.db.repository.impl.TestRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.service.TestService;

public class TestServiceImpl implements TestService {

    TestRepository repository;

    public TestServiceImpl() {
        this.repository = new TestRepositoryImpl();
    }

    @Override
    public int save(Test test) {
       return repository.save(test);
    }

    @Override
    public Test findTestById(Integer id) {
        return repository.findTestById(id);
    }
}
