package com.epam.finaltack.ivanvertylo.db.service;

import com.epam.finaltack.ivanvertylo.db.entity.User;

public interface UserService {
    User validateUser(String login, String password);
}
