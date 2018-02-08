package com.boge.user.service.impl;

import com.boge.user.dao.UserDao;
import com.boge.user.domain.User;
import com.boge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by boge on 2017/12/26.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByLogName(User user) {
        return userDao.findByLogName(user.getLogname());
    }

    @Override
    public boolean checkLogin(String logname, String password) {
        String key = "user_" + logname;
        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        Boolean hasKey = redisTemplate.hasKey(key);

        if (hasKey) {
            User user = valueOperations.get(key);
            if (null != user) {
                if (logname.equalsIgnoreCase(user.getLogname()) && password.equalsIgnoreCase(user.getPassword())) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        User user = userDao.findByLogName(logname);
        if (null != user && logname.equalsIgnoreCase(user.getLogname()) && password.equalsIgnoreCase(user.getPassword())) {
            valueOperations.set(key, user, 30, TimeUnit.MINUTES);
            return true;
        }
        return false;
    }
}
