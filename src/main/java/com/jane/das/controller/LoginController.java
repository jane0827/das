package com.jane.das.controller;

import com.jane.das.model.JsonModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public JsonModel doLogin(String userName, String password){
        JsonModel model = new JsonModel();
        return  model;
    }
}