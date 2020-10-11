package com.epam.finaltack.ivanvertylo.db;

public class Query {

    private Query() { }

    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login = ?;";
    public static final String SQL_SAVE_USER = "INSERT INTO user(login, password, username, role, is_blocked) VALUES(?,?,?,?,?);";
    public static final String SQL_SAVE_TEST = "INSERT INTO test(name, author, is_public) VALUES(?,?,?);";
    public static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM test WHERE test.id = ?;";
    public static final String SQL_FIND_TEST_BY_AUTHOR = "SELECT * FROM test WHERE test.author = ?;";
    public static final String SQL_UPDATE_USER = "UPDATE user SET login=?,username=?,role=?,is_blocked=? WHERE id=?";
    public static final String SQL_FIND_ANSWERS_BY_QUESTION_ID = "SELECT * FROM answer WHERE question_id = ?;";
    public static final String SQL_FIND_QUESTIONS_BY_TEST_ID = "SELECT * FROM question WHERE test_id = ?;";
    public static final String SQL_SAVE_QUESTION = "INSERT INTO question(name, point, test_id) VALUES(?,?,?);";
    public static final String SQL_SAVE_ANSWER = "INSERT INTO answer(name, is_right, question_id) VALUES(?,?,?);";
    public static final String SQL_UPDATE_ANSWER = "UPDATE answer SET name=?,is_right=? WHERE id=?";
    public static final String SQL_UPDATE_QUESTION = "UPDATE question SET name=? WHERE id=?";
    public static final String SQL_FIND_QUESTION_BY_ID = "SELECT * FROM question WHERE id = ?;";
    public static final String SQL_UPDATE_TEST = "UPDATE test SET name=?,subject=?,is_public=?,time=? WHERE id=?";


    public static final String ID = "id";
    //USER
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String USER_USERNAME = "username";
    public static final String USER_BLOCKED = "is_blocked";
    //TEST
    public static final String TEST_NAME = "name";
    public static final String TEST_SUBJECT = "subject";
    public static final String TEST_TIME = "time";
    public static final String TEST_AUTHOR = "author";
    public static final String TEST_PUBLIC = "is_public";
    //QUESTION
    public static final String QUESTION_NAME = "name";
    public static final String QUESTION_POINT = "point";
    public static final String QUESTION_TEST_ID = "test_id";
    //ANSWER
    public static final String ANSWER_NAME = "name";
    public static final String ANSWER_RIGHT = "is_right";
    public static final String ANSWER_QUESTION_ID = "question_id";
}
