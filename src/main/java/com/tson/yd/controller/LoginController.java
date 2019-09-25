package com.tson.yd.controller;

import com.tson.yd.service.login.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("LoginController")
@RequestMapping("/v1/login")
@Api(value = "LoginController 账号", description = "login", tags = "login")
public class LoginController {

    @Autowired
    private LoginService loginService;

}
