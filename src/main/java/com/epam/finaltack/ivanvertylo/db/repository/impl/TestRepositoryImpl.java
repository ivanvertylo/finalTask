package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.repository.TestRepository;
import org.apache.log4j.Logger;

import java.sql.*;

public class TestRepositoryImpl implements TestRepository {

    private static final Logger LOGGER = Logger.getLogger(TestRepository.class);

    @Override
    public int save(Test test) {
        Connection con = null;
        PreparedStatement prst = null;
        ResultSet rs = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_SAVE_TEST, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, test.getName());
            prst.setString(2, test.getAuthor());
            prst.setBoolean(3, test.getPublic());
            if (prst.executeUpdate() > 0){
                try(ResultSet resultSet = prst.getGeneratedKeys()){
                    if (resultSet.next()){
                        test.setId(resultSet.getInt(1));
                    }
                }
            }
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
            LOGGER.error(e.getMessage());
        } finally {
            dbManager.close(prst,con);
        }
        return test.getId();
    }

    @Override
    public Test findTestById(Integer id) {
        Test requests = new Test();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_TEST_BY_ID);
            prst.setInt(1, id);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests = extractTest(rs);
            }
            con.commit();

        } catch (Exception e) {
            dbManager.rollback(con);
            LOGGER.error(e.getMessage());
        } finally {
            dbManager.close(prst,con,rs);
        }
        return requests;
    }
    private Test extractTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt(Query.ID));
        test.setName(rs.getString(Query.TEST_NAME));
        test.setSubject(rs.getString(Query.TEST_SUBJECT));
        test.setAuthor(rs.getString(Query.TEST_AUTHOR));
        test.setPublic(rs.getBoolean(Query.TEST_PUBLIC));
        return test;
    }
}
