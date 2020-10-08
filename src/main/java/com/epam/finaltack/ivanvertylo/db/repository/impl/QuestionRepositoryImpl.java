package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Question;
import com.epam.finaltack.ivanvertylo.db.repository.AnswerRepository;
import com.epam.finaltack.ivanvertylo.db.repository.QuestionRepository;
import com.epam.finaltack.ivanvertylo.db.service.AnswerService;
import com.epam.finaltack.ivanvertylo.db.service.impl.AnswerServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepositoryImpl implements QuestionRepository {

    private final AnswerService answerService;

    public QuestionRepositoryImpl() {
        answerService = new AnswerServiceImpl();
    }

    @Override
    public List<Question> findQuestionsByTestId(Integer testId) {
        List<Question> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_QUESTIONS_BY_TEST_ID);
            prst.setInt(1, testId);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests.add(extractQuestion(rs));
            }
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst,con,rs);
        }
        return requests;
    }

    @Override
    public int saveQuestion(Question question) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_SAVE_QUESTION, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, question.getName());
            prst.setInt(2, question.getPoint());
            prst.setInt(3, question.getTestId());
            if (prst.executeUpdate() > 0){
                try(ResultSet resultSet = prst.getGeneratedKeys()){
                    if (resultSet.next()){
                        question.setId(resultSet.getInt(1));
                    }
                }
            }
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst,con);
        }
        return question.getId();
    }

    private Question extractQuestion(ResultSet rs) throws SQLException {
        Question question = new Question();
        question.setId(rs.getInt(Query.ID));
        question.setName(rs.getString(Query.QUESTION_NAME));
        question.setPoint(rs.getInt(Query.QUESTION_POINT));
        question.setTestId(rs.getInt(Query.QUESTION_TEST_ID));
        question.setAnswers(answerService.findAnswersByQuestionsId(question.getId()));
        return question;
    }
}
