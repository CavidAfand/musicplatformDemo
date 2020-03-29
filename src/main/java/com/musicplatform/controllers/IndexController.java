package com.musicplatform.controllers;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.entities.markers.PlatformUser;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller
public class IndexController {

    @Autowired
    ListenerRepository listenerRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    BandRepository bandRepository;


    @GetMapping("/")
    public ModelAndView enterIndexPage(Authentication authentication) {

        ModelAndView mv = new ModelAndView("/index");

        PlatformUser platformUser = getPlatformUser(authentication.getName());

        System.out.println("Buralardayam");


        if (platformUser instanceof Listener) {
            System.out.println("1 - listener");
            mv.addObject("user",platformUser);
            mv.addObject("platformUser", "listener");

        }
        else if (platformUser instanceof Musician) {
            System.out.println("2 - musician");
            mv.addObject("user",platformUser);
            mv.addObject("platformUser", "musician");

        }
        else if (platformUser instanceof Band) {
            System.out.println("3 - band");
            mv.addObject("user",platformUser);
            mv.addObject("platformUser", "band");

        }
        else {
            ModelAndView errorView = new ModelAndView("redirect:error");
            errorView.addObject("error", "Something went wrong.");
            return errorView;
        }

        return mv;
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
