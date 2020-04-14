package com.musicplatform.controllers;

import com.musicplatform.entities.Listener;
import com.musicplatform.repositories.ListenerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
//@RequestMapping("/listener/")
public class ListenerHomeController {

    @Autowired
    ListenerRepository listenerRepository;

    @GetMapping("/listener/index")
    public String getListenerIndex(Authentication auth, Model model) {

        log.info(auth.getName() + " entered platform");

        model.addAttribute("user", getListener(auth.getName()));
        model.addAttribute("platformUser", "listener");
        return "/listener/index";
    }

    private Listener getListener(String username) {
        return listenerRepository.findByUsername(username);
    }
}
