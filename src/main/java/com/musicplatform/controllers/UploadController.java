package com.musicplatform.controllers;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.exceptions.NotImageException;
import com.musicplatform.forms.BandForm;
import com.musicplatform.forms.ListenerForm;
import com.musicplatform.forms.MusicianForm;
import com.musicplatform.modelAttributes.SignUpData;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicianRepository;
import com.musicplatform.services.emailService.EmailService;
import com.musicplatform.services.fileServices.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;

@Controller
public class UploadController {

    @Autowired
    ListenerRepository listenerRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    BandRepository bandRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;


    @PostMapping("/upload_listener")
    public ModelAndView uploadListener(
            @ModelAttribute("listener") ListenerForm listenerForm,
            @RequestParam("date") String date,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            BindingResult bindingResult
    )
    {
        if (bindingResult.hasErrors()) {
            return anyError();
        }

        if (SignUpData.randomNumber != Integer.parseInt(code)) {
            return verificationCodeError();
        }



        String imagePath = null;
        try {
            imagePath = ImageUpload.checkAndSaveImage(image, SignUpData.username);
        }
        catch  (NotImageException ex) {
            return notImageError();
        } catch (IOException ex) {
            return imageUploadError();
        }

        if (emailService.sendUsername(SignUpData.email, SignUpData.username) != true) {
            return anyError();
        }

        listenerRepository.save(new Listener(
                listenerForm.getName(), listenerForm.getSurname(), SignUpData.email, SignUpData.username , passwordEncoder.encode(listenerForm.getPassword()),
                LocalDate.parse(date), listenerForm.getGender(), imagePath
        ));

        SignUpData.successfulRegistered = true;

        ModelAndView mv = new ModelAndView("redirect:successful_registration");
        mv.addObject("username", SignUpData.username);
        return mv;
    }

    @PostMapping("/upload_musician")
    public ModelAndView uploadMusician(
            @ModelAttribute("musician") MusicianForm musicianForm,
            @RequestParam("date") String date,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            BindingResult bindingResult
            )
    {
        if (bindingResult.hasErrors()) {
            return anyError();
        }

        if (SignUpData.randomNumber != Integer.parseInt(code)) {
            return verificationCodeError();
        }


        String imagePath = null;
        try {
            imagePath = ImageUpload.checkAndSaveImage(image, SignUpData.username);
        }
        catch  (NotImageException ex) {
            return notImageError();
        } catch (IOException ex) {
            return imageUploadError();
        }

        if (emailService.sendUsername(SignUpData.email, SignUpData.username) != true) {
            return anyError();
        }

        musicianRepository.save(new Musician(
                musicianForm.getName(), musicianForm.getSurname(), SignUpData.email, SignUpData.username, passwordEncoder.encode(musicianForm.getPassword()),
                musicianForm.getNickname(), LocalDate.parse(date), musicianForm.getGender(), imagePath
        ));

        SignUpData.successfulRegistered = true;

        ModelAndView mv = new ModelAndView("redirect:successful_registration");
        mv.addObject("username", SignUpData.username);

        return mv;
    }

    @PostMapping("/upload_band")
    public ModelAndView uploadBand(
            @ModelAttribute("band") BandForm bandForm,
            @RequestParam("date") String date,
            @RequestParam("image") MultipartFile image,
            @RequestParam("code") String code,
            BindingResult bindingResult
    ) {

        if (bindingResult.hasErrors()) {
            return anyError();
        }

        if (SignUpData.randomNumber != Integer.parseInt(code)) {
            return verificationCodeError();
        }

        String imagePath = null;
        try {
            imagePath = ImageUpload.checkAndSaveImage(image, SignUpData.username);
        }
        catch  (NotImageException ex) {
            return notImageError();
        } catch (IOException ex) {
            return imageUploadError();
        }

        if (emailService.sendUsername(SignUpData.email, SignUpData.username) != true) {
            return anyError();
        }

        bandRepository.save(new Band(
                bandForm.getBandName(), SignUpData.email, SignUpData.username, passwordEncoder.encode(bandForm.getPassword()),
                LocalDate.parse(date), imagePath
        ));

        SignUpData.successfulRegistered = true;

        ModelAndView mv = new ModelAndView("redirect:successful_registration");
        mv.addObject("username", SignUpData.username);

        return mv;
    }


    private ModelAndView anyError() {
        ModelAndView errorView = new ModelAndView("redirect:error");
        errorView.addObject("error","error has happened");
        return errorView;
    }

    private ModelAndView verificationCodeError() {
        ModelAndView errorView = new ModelAndView("redirect:error");
        errorView.addObject("error","Error has happened. Verfication code is wrong");
        return errorView;
    }

    private ModelAndView notImageError() {
        ModelAndView mv = new ModelAndView("redirect:error");
        mv.addObject("error", "File you uploaded is not image. Please try to sign up again.");
        return mv;
    }

    private ModelAndView imageUploadError() {
        ModelAndView mv = new ModelAndView("redirect:error");
        mv.addObject("error", "Error has happened. Please try to sign up again.");
        return mv;
    }
}
