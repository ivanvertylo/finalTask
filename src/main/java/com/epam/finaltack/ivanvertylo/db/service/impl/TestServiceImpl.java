package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.repository.TestRepository;
import com.epam.finaltack.ivanvertylo.db.repository.impl.TestRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.service.TestService;

import java.util.List;

public class TestServiceImpl implements TestService {

    private final TestRepository repository;

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

    @Override
    public List<Test> findTestsByAuthor(String author) {
        return repository.findTestsByAuthor(author);
    }

    @Override
    public void updateTest(Test test) {
        repository.updateTest(test);
    }
}
