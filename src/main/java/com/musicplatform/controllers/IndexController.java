package com.musicplatform.controllers;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.entities.markers.PlatformUser;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

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
    public String enterIndexPage(Authentication authentication, Model model, @ModelAttribute("type") String type) {

        PlatformUser platformUser = getPlatformUser(authentication.getName());

        System.out.println("Buralardayam");


        if (platformUser instanceof Listener) {
            log.info(authentication.getName() + " entered platform");
            return "redirect:listener/index";
        }
        else if (platformUser instanceof Musician) {
            log.info(authentication.getName() + " entered platform");
            return "redirect:musician/index";
        }
        else if (platformUser instanceof Band) {
            log.info(authentication.getName() + " entered platform");
            return "redirect:band/index";
        }
        else {
            log.error("Error happened in index page");
            model.addAttribute("error", "Something went wrong.");
            return "redirect:error";
        }

    }

    private PlatformUser getPlatformUser(String username) {
        Listener listener = listenerRepository.findByUsername(username);
        if (listener != null) return listener;

        Musician musician = musicianRepository.findByUsername(username);
        if (musician != null) return musician;

        Band band = bandRepository.findByUsername(username);
        if (band != null) return band;

        return null;
    }


}
