package com.boge.user.dao;

import com.boge.user.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by boge on 2017/12/27.
 */
public interface UserDao {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> findAll();

    /**
     * 根据登录名查找用户
     *
     * @return
     */
    User findByLogName(@Param("logname") String logname);
}
