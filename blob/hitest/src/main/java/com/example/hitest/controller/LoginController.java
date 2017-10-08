package com.example.hitest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @PostMapping(value = "/login")
    public String Login(HttpServletRequest request) {


        return "success";
    }
}
