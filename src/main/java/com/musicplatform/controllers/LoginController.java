package com.musicplatform.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @GetMapping("login")
    public ModelAndView doGet() {
        return new ModelAndView("login");
    }

}
