package com.epam.finaltack.ivanvertylo.db;

public class Query {

    private Query() { }

    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE user.login = ?;";
    public static final String SQL_SAVE_USER = "INSERT INTO user(login, password, username, role) VALUES(?,?,?,?);";
}
