package com.epam.finaltack.ivanvertylo.db.repository;

import com.epam.finaltack.ivanvertylo.db.entity.User;

public interface UserRepository {
    User findUserByLogin(String login);
    int saveUser(User user);
}
