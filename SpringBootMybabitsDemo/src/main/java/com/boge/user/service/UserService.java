package com.boge.user.service;

import com.boge.user.domain.User;

import java.util.List;

/**
 * Created by boge on 2017/12/26.
 */
public interface UserService {
    List<User> findAll();

    User findByLogName(User user);

    boolean checkLogin(String logname, String password);
}
