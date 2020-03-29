package com.musicplatform.controllers;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.forms.BandForm;
import com.musicplatform.forms.ListenerForm;
import com.musicplatform.forms.MusicianForm;
import com.musicplatform.modelAttributes.SignUpData;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import com.musicplatform.services.emailService.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
@SessionAttributes({"email","number"})
public class SignUpController {

    @Autowired
    EmailService emailService;

    @Autowired
    ListenerRepository listenerRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    BandRepository bandRepository;

    @PostMapping("/signup_listener")
    public ModelAndView postListenerSignUp(@RequestParam("email") String email) {

        Listener listener = listenerRepository.findByEmail(email);

        if (listener != null) {
            return alreadySignedUp();
        }

        Random random = new Random();
        int randomNumber = random.nextInt(99999);
        if (emailService.sendSignUpCode(email, randomNumber) != true) {
            return verificatioCodeError();
        }

        SignUpData.listenerSignUpRequest = true;
        SignUpData.email = email;
        SignUpData.randomNumber = randomNumber;
        SignUpData.username = "listener_" + email;

        return new ModelAndView("sing_up/signup_listener");
    }

    @GetMapping("/signup_listener")
    public ModelAndView getListenerSignUp(Model model) {
        if (SignUpData.listenerSignUpRequest == false) {
            ModelAndView mv = new ModelAndView("redirect:error");
            mv.addObject("error", "Page not found");
            return mv;
        }
        model.addAttribute("listener", new ListenerForm());
        SignUpData.listenerSignUpRequest = false;
        return new ModelAndView("redirect:sing_up/signup_listener");
    }

    @PostMapping("/signup_musician")
    public ModelAndView musicianSignUp(@RequestParam("email") String email) {
        Musician musician = musicianRepository.findByEmail(email);

        if (musician != null) {
            return alreadySignedUp();
        }

        Random random = new Random();
        int randomNumber = random.nextInt(99999);
        if (emailService.sendSignUpCode(email, randomNumber) != true) {
            return verificatioCodeError();
        }

        SignUpData.musicianSignUpRequest = true;
        SignUpData.email = email;
        SignUpData.randomNumber = randomNumber;
        SignUpData.username = "music_" + email;


        return new ModelAndView("sing_up/signup_musician");
    }

    @GetMapping("/signup_musician")
    public ModelAndView getMusicianSignUp(Model model) {

        if (SignUpData.musicianSignUpRequest == false) {
            ModelAndView mv = new ModelAndView("redirect:error");
            mv.addObject("error", "Page not found");
            return mv;
        }

        SignUpData.musicianSignUpRequest = true;

        model.addAttribute("musician", new MusicianForm());

        return new ModelAndView("redirectsing_up/signup_musician");
    }

    @PostMapping("/signup_band")
    public ModelAndView bandSignUp(@RequestParam("email") String email) {
        Band band = bandRepository.findByEmail(email);

        if (band != null) {
            return alreadySignedUp();
        }

        Random random = new Random();
        int randomNumber = random.nextInt(99999);
        if (emailService.sendSignUpCode(email, randomNumber) != true) {
            return verificatioCodeError();
        }

        SignUpData.bandSignUpRequest = true;
        SignUpData.email = email;
        SignUpData.randomNumber = randomNumber;
        SignUpData.username = "band_" + email + "";

        return new ModelAndView("sing_up/signup_band");
    }

    @GetMapping("/signup_band")
    public ModelAndView getBandSignUp(Model model) {

        if (SignUpData.bandSignUpRequest == false) {
            ModelAndView mv = new ModelAndView("redirect:error");
            mv.addObject("error", "Page not found");
            return mv;
        }

        SignUpData.bandSignUpRequest = true;

        model.addAttribute("band", new BandForm());

        return new ModelAndView("redirect:sing_up/signup_band");

    }

    @ModelAttribute("listener")
    public ListenerForm getListenerForm() {
        return new ListenerForm();
    }

    @ModelAttribute("musician")
    public MusicianForm getMusicianForm() {
        return new MusicianForm();
    }

    @ModelAttribute("band")
    public BandForm getBandForm() {
        return new BandForm();
    }

    private ModelAndView alreadySignedUp() {
        ModelAndView errorView = new ModelAndView("redirect:error");
        errorView.addObject("error", "You have already signed up.");
        return errorView;
    }

    private ModelAndView verificatioCodeError() {
        ModelAndView errorView = new ModelAndView("redirect: error");
        errorView.addObject("error", "Verification code cannot be send");
        return errorView;
    }
}
