package com.musicplatform.controllers;


import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    ListenerRepository listenerRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    BandRepository bandRepository;


    @GetMapping("/")
    public String enterIndexPage(Authentication authentication, RedirectAttributes redirectAttributes) {
        if (authentication.getName().startsWith("listener")) {
            return "redirect:/listener/index";
        }
        else if (authentication.getName().startsWith("music")) {
            return "redirect:/musician/index";
        }
        else if (authentication.getName().startsWith("band")) {
            return "redirect:/band/index";
        }
        else {
            log.error("Error happened in index page");
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return "redirect:error";
        }
    }

}
