package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.CountSubjects;
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

    @Override
    public List<Test> findAllTestNameSort(String subject, Integer pagination, String upDown, Integer offset) {
        return repository.findTestsByNameSort(subject,pagination,upDown,offset);
    }

    @Override
    public List<Test> findAllTestByQuestionSort(String subject, Integer pagination, String upDown, Integer offset) {
        return repository.findTestsByNumberOfQuestionSort(subject,pagination,upDown,offset);
    }

    @Override
    public List<Test> findTestsByComplexitySort(String subject, Integer pagination, String upDown, Integer offset) {
        return repository.findTestsByComplexitySort(subject,pagination,upDown,offset);
    }

    @Override
    public List<CountSubjects> findSubjectsCounts(String substr) {
        return repository.findSubjectsCounts(substr);
    }

    @Override
    public Integer getAllTestsCount() {
        return repository.getAllTestsCount();
    }
}
