package com.musicplatform.controllers;

import com.musicplatform.modelAttributes.SignUpData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/user_sessions")
    public String getUsers(Model model) {
        List<String> list = sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(Object::toString)
                .collect(Collectors.toList());
        model.addAttribute("list", list);
        return "/user_sessions";
    }


}
