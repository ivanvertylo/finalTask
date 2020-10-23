package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount;

import java.util.List;

public interface UserRepository {
    User findUserByLogin(String login);

    int saveUser(User user);

    void updateUser(User user);

    List<UsersTestCount> findAllUsers();

    int getCountTestForUser(String login);
}
