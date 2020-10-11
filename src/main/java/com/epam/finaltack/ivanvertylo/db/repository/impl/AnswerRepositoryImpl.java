package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.Constant;
import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Answer;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.repository.AnswerRepository;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepositoryImpl  implements AnswerRepository {
    @Override
    public List<Answer> findAnswersByQuestionId(Integer questionId) {
        List<Answer> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_ANSWERS_BY_QUESTION_ID);
            prst.setInt(1, questionId);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests.add(extractAnswer(rs));
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
    public void saveAnswer(Answer answer) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_SAVE_ANSWER);
            prst.setString(1, answer.getName());
            prst.setBoolean(2, answer.getRight());
            prst.setInt(3, answer.getQuestionId());
            prst.executeUpdate();
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst,con);
        }
    }

    @Override
    public void updateAnswer(Answer answer) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_UPDATE_ANSWER);
            prst.setString(1, answer.getName());
            prst.setBoolean(2, answer.getRight());
            prst.setInt(3, answer.getId());
            prst.executeUpdate();
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst,con);
        }
    }

    private Answer extractAnswer(ResultSet rs) throws SQLException {
        Answer answer = new Answer();
        answer.setId(rs.getInt(Query.ID));
        answer.setName(rs.getString(Query.ANSWER_NAME));
        answer.setRight(rs.getBoolean(Query.ANSWER_RIGHT));
        answer.setQuestionId(rs.getInt(Query.ANSWER_QUESTION_ID));
        return answer;
    }
}
