package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount;

import java.util.List;

public interface UserService {
    User validateUser(User user);

    int registerUser(User user);

    User findUserByLogin(String login);

    void updateUser(User user);

    List<UsersTestCount> findAllUsers();
}
