package com.example.hitest.controller;

import com.example.hitest.model.User;
import com.example.hitest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class TestController {

    @Autowired
    private  UserService userService;

    @GetMapping(value = "/getUser")
    public String userTest() {
        List<User> getUser = userService.getUser();
        String name = "";

        for (User user : getUser) {
            name = user.getName();
        }
        return name;
    }
}
