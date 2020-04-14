package com.musicplatform.controllers.musician;

import com.musicplatform.entities.Musician;
import com.musicplatform.exceptions.NotImageException;
import com.musicplatform.modelAttributes.SignUpData;
import com.musicplatform.repositories.MusicianRepository;
import com.musicplatform.services.fileServices.ImageUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Controller
public class MusicianEditController {

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/musician/change_image")
    public String changePhoto(@RequestParam("image") MultipartFile image, @RequestParam("original_name") String name,
                              Authentication auth, final RedirectAttributes redirectAttributes)
    {

        try {
            String newImageName = ImageUpload.checkAndChangeImage(image, auth.getName(), name);
            if (!newImageName.equals(name)) {
                Musician musician = musicianRepository.findByUsername(auth.getName());
                musician.setImagePath(newImageName);
                musicianRepository.save(musician);
            }
            log.info(auth.getName() + " has updated image successfully.");
        }
        catch  (NotImageException ex) {
            log.error("Uploaded file is not image");
            redirectAttributes.addFlashAttribute("error","Your uploaded file is not image");
            return "redirect:../error";
        } catch (IOException ex) {
            log.error("Error has happened to upload image");
            redirectAttributes.addFlashAttribute("error","Error has happened. Please try to sign up");
            return "redirect:../error";
        }

        redirectAttributes.addFlashAttribute("action","photo");

        return "redirect:/musician/index";

    }

    @PostMapping("/musician/change_password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 final RedirectAttributes redirectAttributes,
                                 Authentication auth) {

        Musician musician = musicianRepository.findByUsername(auth.getName());


        if (!passwordEncoder.matches(currentPassword, musician.getPassword())) {
            log.warn(auth.getName() + " entered wrong password to change his password");
            redirectAttributes.addFlashAttribute("error","You entered wrong password");
            return "redirect:../error";
        }

        musician.setPassword(passwordEncoder.encode(newPassword));
        musicianRepository.save(musician);
        log.info(auth.getName() + " changed his password");

        redirectAttributes.addFlashAttribute("action", "password");

        return "redirect:/musician/index";
    }

    @PostMapping("/musician/edit_information")
    public String changeInformation(@RequestParam("name") String name,
                                    @RequestParam("surname") String surname,
                                    @RequestParam("nickname") String nickName,
                                    @RequestParam("date") String date,
                                    Authentication auth,
                                    RedirectAttributes redirectAttributes) {
        Musician musician = musicianRepository.findByUsername(auth.getName());
        musician.setName(name);
        musician.setSurname(surname);
        musician.setNickName(nickName);
        musician.setDate(LocalDate.parse(date));
        musicianRepository.save(musician);
        redirectAttributes.addFlashAttribute("action","editInfo");
        return "redirect:/musician/index";
    }


}
