package com.jane.das.controller;

import com.jane.das.commons.model.JsonModel;
import com.jane.das.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/***
 * 用于控制页面跳转
 */
@Controller
public class PageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String toLogin(){
        return "test";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "loginName") String loginName,
                           @RequestParam(value = "password") String password){
        if (userService.checkLoginUser(loginName,password)){
            return "/test";
        }else {
            return "test2";
        }
    }
}
