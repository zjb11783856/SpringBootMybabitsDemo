package com.boge.user.controller;

import com.boge.user.domain.User;
import com.boge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by boge on 2017/12/22.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sayHello")
    public String sayHello() {

        return "Hello World!";
    }

    @RequestMapping(value = "/doLogin", method = RequestMethod.GET)
    public boolean doLogin(@RequestParam String logname, @RequestParam String password) {
        return userService.checkLogin(logname, password);
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public List<User> findUserList() {
        return userService.findAll();
    }

}
