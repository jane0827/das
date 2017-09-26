package com.jane.das.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 用于控制页面跳转
 */
@Controller
public class PageController {
    @RequestMapping("/login")
    public String toTest(Model model){
        return "test";
    }
}
