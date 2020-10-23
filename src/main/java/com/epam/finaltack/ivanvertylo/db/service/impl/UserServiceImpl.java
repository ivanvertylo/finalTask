package com.epam.finaltack.ivanvertylo.db.service.impl;

import com.epam.finaltack.ivanvertylo.db.entity.User;
import com.epam.finaltack.ivanvertylo.db.entity.UsersTestCount;
import com.epam.finaltack.ivanvertylo.db.repository.UserRepository;
import com.epam.finaltack.ivanvertylo.db.repository.impl.UserRepositoryImpl;
import com.epam.finaltack.ivanvertylo.db.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    @Override
    public User validateUser(User user) {
        User userResp = userRepository.findUserByLogin(user.getLogin());
        if (userResp.getPassword() != null) {
            return userResp.getPassword().equals(DigestUtils.md5Hex(user.getPassword())) ? userResp : null;
        }
        return null;
    }


    @Override
    public int registerUser(User user) {
        if (Pattern.compile("[A-Za-z0-9]+").matcher(user.getLogin()).find() &&
                Pattern.compile("[A-Za-z0-9]+").matcher(user.getPassword()).find()) {
            if (userRepository.findUserByLogin(user.getLogin()).getPassword() == null) {
                user.setUsername(user.getLogin());
                return userRepository.saveUser(user);
            }
        }
        return 0;
    }

    @Override
    public User findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public List<UsersTestCount> findAllUsers() {
        List<UsersTestCount> userTestCount =  userRepository.findAllUsers();
        for(int i =0; i < userTestCount.size(); i++){
            userTestCount.get(i).setCount(userRepository.getCountTestForUser(userTestCount.get(i).getUser().getLogin()));
        }
        return userTestCount;
    }
}
