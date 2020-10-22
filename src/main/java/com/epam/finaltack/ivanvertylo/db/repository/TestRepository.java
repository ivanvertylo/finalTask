package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.CountSubjects;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.entity.TestPoints;

import java.util.List;

public interface TestRepository {
    int save(Test test);

    Test findTestById(Integer id);

    List<Test> findTestsByAuthor(String author);

    List<Test> findTestsByNameSort(String subject, Integer pagination, String upDown, Integer offset);

    List<Test> findTestsByNumberOfQuestionSort(String subject, Integer pagination, String upDown, Integer offset);

    List<Test> findTestsByComplexitySort(String subject, Integer pagination, String upDown, Integer offset);

    List<CountSubjects> findSubjectsCounts(String substr);

    void updateTest(Test test);

    Integer getAllTestsCount(String subject);

    void setPoints(Integer idUser, Integer idTest, Integer points);

    Integer getPoints(Integer idUser, Integer idTest);

    List<TestPoints> findTestPointsByUserId(Integer id);

    void deleteTest(Integer testId);

    Test findTestByName(String name);
}
