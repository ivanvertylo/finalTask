package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.CountSubjects;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.entity.TestPoints;

import java.util.List;

public interface TestService {
    int save(Test test);
    Test findTestById(Integer id);
    List<Test> findTestsByAuthor(String author);
    void updateTest(Test test);
    List<Test> findAllTestNameSort(String subject, Integer pagination, String upDown, Integer offset);
    List<Test> findAllTestByQuestionSort(String subject, Integer pagination, String upDown, Integer offset);
    List<Test> findTestsByComplexitySort(String subject, Integer pagination, String upDown, Integer offset);
    List<CountSubjects> findSubjectsCounts(String substr);
    Integer getAllTestsCount(String subject);
    void setPoints(Integer idUser, Integer idTest, Integer points);
    Integer getPoints(Integer idUser, Integer idTest);
    List<TestPoints> findTestPointsByUserLogin(String login);
    void deleteTest(Integer testId);
    boolean findTestByName(String name);
}
