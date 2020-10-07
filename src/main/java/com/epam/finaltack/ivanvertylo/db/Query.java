package com.epam.finaltack.ivanvertylo.db;

public class Query {

    private Query() { }

    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login = ?;";
    public static final String SQL_SAVE_USER = "INSERT INTO user(login, password, username, role, is_blocked) VALUES(?,?,?,?,?);";
    public static final String SQL_SAVE_TEST = "INSERT INTO test(name, author, is_public) VALUES(?,?,?);";
    public static final String SQL_FIND_TEST_BY_ID = "SELECT * FROM test WHERE test.id = ?;";
    public static final String SQL_FIND_TEST_BY_AUTHOR = "SELECT * FROM test WHERE test.author = ?;";
    public static final String SQL_UPDATE_USER = "UPDATE user SET login=?,username=?,role=?,is_blocked=? WHERE id=?";

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
}
