package com.musicplatform.controllers;

import com.musicplatform.modelAttributes.SignUpData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuxiliaryController {

    @GetMapping("/successful_registration")
    public ModelAndView getSuccessfulRegistration() {
        if (SignUpData.successfulRegistered == false) {
            ModelAndView errorView = new ModelAndView("redirect:error");
            errorView.addObject("error", "Page not found");
            return errorView;
        }
        SignUpData.successfulRegistered = false;
        return new ModelAndView("/successful_registration");
    }


    @GetMapping("testImage")
    public String test() {
        return "testImage";
    }

}
