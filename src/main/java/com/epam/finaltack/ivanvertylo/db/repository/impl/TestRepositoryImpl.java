package com.epam.finaltack.ivanvertylo.db.repository.impl;

import com.epam.finaltack.ivanvertylo.db.DBManager;
import com.epam.finaltack.ivanvertylo.db.Query;
import com.epam.finaltack.ivanvertylo.db.entity.CountSubjects;
import com.epam.finaltack.ivanvertylo.db.entity.Test;
import com.epam.finaltack.ivanvertylo.db.entity.TestPoints;
import com.epam.finaltack.ivanvertylo.db.repository.TestRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepositoryImpl implements TestRepository {

    private static final Logger LOG = Logger.getLogger(TestRepositoryImpl.class);

    @Override
    public int save(Test test) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_SAVE_TEST, Statement.RETURN_GENERATED_KEYS);
            prst.setString(1, test.getName());
            prst.setString(2, test.getAuthor());
            prst.setBoolean(3, test.getIsPublic());
            if (prst.executeUpdate() > 0) {
                try (ResultSet resultSet = prst.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        test.setId(resultSet.getInt(1));
                    }
                }
            }
            con.commit();
            LOG.info("Performing save "+test.getId());
        } catch (Exception e) {
            LOG.error("Performing save");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con);
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
            LOG.info("Performing findTestById "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestById");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    @Override
    public List<Test> findTestsByAuthor(String author) {
        List<Test> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_TEST_BY_AUTHOR);
            prst.setString(1, author);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests.add(extractTest(rs));
            }
            con.commit();
            LOG.info("Performing findTestsByAuthor "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestsByAuthor");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    @Override
    public List<Test> findTestsByNameSort(String subject, Integer pagination, String upDown, Integer offset) {
        List<Test> requests = new ArrayList<>();
        String substr = !subject.equals("") ? "WHERE subject='" + subject + "' AND" : "where";
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            rs = st.executeQuery("SELECT * FROM test " + substr + " is_public = true order by name " + upDown + " limit " + pagination + "  OFFSET " + offset + ";");
            while (rs.next()) {
                requests.add(extractTest(rs));
            }
            con.commit();
            LOG.info("Performing findTestsByNameSort "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestsByNameSort");
            dbManager.rollback(con);
        } finally {
            dbManager.close(st, con, rs);
        }
        return requests;
    }

    @Override
    public List<Test> findTestsByNumberOfQuestionSort(String subject, Integer pagination, String upDown, Integer offset) {
        List<Test> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        String substr = !subject.equals("") ? "WHERE subject='" + subject + "' AND" : "where";
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            rs = st.executeQuery("SELECT * FROM test " + substr + "  is_public = true order by (select count(*) from question where question.test_id = test.id) " + upDown + " limit " + pagination + "  OFFSET " + offset + ";");
            while (rs.next()) {
                requests.add(extractTest(rs));
            }
            con.commit();
            LOG.info("Performing findTestsByNumberOfQuestionSort "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestsByNumberOfQuestionSort");
            dbManager.rollback(con);
        } finally {
            dbManager.close(st, con, rs);
        }
        return requests;
    }

    @Override
    public List<Test> findTestsByComplexitySort(String subject, Integer pagination, String upDown, Integer offset) {
        List<Test> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        String substr = !subject.equals("") ? "WHERE subject='" + subject + "' AND" : "where";
        Statement st = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            st = con.createStatement();
            con.setAutoCommit(false);
            rs = st.executeQuery("SELECT * FROM test " + substr + " is_public = true order by -1*(test.time/(select count(*) from question where question.test_id = test.id)) " + upDown + " limit " + pagination + "  OFFSET " + offset + ";");
            while (rs.next()) {
                requests.add(extractTest(rs));
            }
            con.commit();
            LOG.info("Performing findTestsByComplexitySort "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestsByComplexitySort");
            dbManager.rollback(con);
        } finally {
            dbManager.close(st, con, rs);
        }
        return requests;
    }

    @Override
    public List<CountSubjects> findSubjectsCounts(String substr) {
        List<CountSubjects> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_SUBJECTS_COUNTS);
            prst.setString(1, substr);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests.add(extractSubjectsCounts(rs));
            }
            con.commit();
            LOG.info("Performing findSubjectsCounts "+requests);
        } catch (Exception e) {
            LOG.error("Performing findSubjectsCounts");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    @Override
    public void updateTest(Test test) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_UPDATE_TEST);
            prst.setString(1, test.getName());
            prst.setString(2, test.getSubject());
            prst.setBoolean(3, test.getIsPublic());
            prst.setInt(4, test.getTime());
            prst.setInt(5, test.getId());
            prst.executeUpdate();
            con.commit();
            LOG.info("Performing updateTest");
        } catch (Exception e) {
            LOG.error("Performing updateTest");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con);
        }
    }

    @Override
    public Integer getAllTestsCount(String subject) {
        int requests = 0;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement st = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            if (!subject.equals("")) {
                st = con.prepareStatement(Query.SQL_GET_ALL_TESTS_BY_PARAM);
                st.setString(1, subject);
            } else {
                st = con.prepareStatement(Query.SQL_GET_ALL_TESTS);
            }
            rs = st.executeQuery();
            while (rs.next()) {
                requests = rs.getInt("count(*)");
            }
            con.commit();
            LOG.info("Performing getAllTestsCount "+requests);
        } catch (Exception e) {
            LOG.error("Performing getAllTestsCount");
            dbManager.rollback(con);
        } finally {
            dbManager.close(st, con, rs);
        }
        return requests;
    }

    @Override
    public void setPoints(Integer idUser, Integer idTest, Integer points) {
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            if (getPoints(idUser, idTest) == null) {
                prst = con.prepareStatement(Query.SQL_SET_POINTS);
            } else {
                prst = con.prepareStatement(Query.SQL_UPDATE_POINTS);
            }
            prst.setInt(1, points);
            prst.setInt(2, idUser);
            prst.setInt(3, idTest);
            prst.executeUpdate();
            con.commit();
            LOG.info("Performing setPoints");
        } catch (Exception e) {
            LOG.error("Performing setPoints");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con);
        }
    }

    @Override
    public Integer getPoints(Integer idUser, Integer idTest) {
        Integer requests = null;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_GET_POINTS);
            prst.setInt(1, idUser);
            prst.setInt(2, idTest);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests = rs.getInt("points");
            }
            con.commit();
            LOG.info("Performing getPoints "+requests);
        } catch (Exception e) {
            LOG.error("Performing getPoints");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    @Override
    public List<TestPoints> findTestPointsByUserId(Integer id) {
        List<TestPoints> requests = new ArrayList<>();
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement prst = null;
        DBManager dbManager = DBManager.getInstance();
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            prst = con.prepareStatement(Query.SQL_FIND_TEST_POINTS_BY_USER_ID);
            prst.setInt(1, id);
            rs = prst.executeQuery();
            while (rs.next()) {
                requests.add(extractTestPoints(rs));
            }
            con.commit();
            LOG.info("Performing findTestPointsByUserId "+requests);
        } catch (Exception e) {
            LOG.error("Performing findTestPointsByUserId");
            dbManager.rollback(con);
        } finally {
            dbManager.close(prst, con, rs);
        }
        return requests;
    }

    private Test extractTest(ResultSet rs) throws SQLException {
        Test test = new Test();
        test.setId(rs.getInt(Query.ID));
        test.setName(rs.getString(Query.TEST_NAME));
        test.setSubject(rs.getString(Query.TEST_SUBJECT));
        test.setAuthor(rs.getString(Query.TEST_AUTHOR));
        test.setIsPublic(rs.getBoolean(Query.TEST_PUBLIC));
        test.setTime(rs.getInt(Query.TEST_TIME));
        return test;
    }

    private CountSubjects extractSubjectsCounts(ResultSet rs) throws SQLException {
        CountSubjects countSubjects = new CountSubjects();
        countSubjects.setCount(rs.getInt("count(*)"));
        countSubjects.setName(rs.getString("subject"));
        return countSubjects;
    }

    private TestPoints extractTestPoints(ResultSet rs) throws SQLException {
        TestPoints testPoints = new TestPoints();
        testPoints.setTest(extractTest(rs));
        testPoints.setPoints(rs.getInt("points"));
        return testPoints;
    }
}
