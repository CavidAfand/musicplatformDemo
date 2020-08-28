package com.musicplatform.controllers.band;

import com.musicplatform.entities.Band;
import com.musicplatform.exceptions.NotImageException;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.services.fileServices.ImageUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
@Controller
public class BandEditController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BandRepository bandRepository;

    @PostMapping("/band/change_image")
    public String changePhoto(@RequestParam("image") MultipartFile image,
                              @RequestParam("original_name") String name,
                              Authentication auth,
                              RedirectAttributes redirectAttributes) {
        try {
            String newImageName = ImageUpload.checkAndChangeImage(image, auth.getName(), name);
            if (!newImageName.equals(name)) {
                Band band = bandRepository.findByUsername(auth.getName());
                band.setImagePath(newImageName);
                bandRepository.save(band);
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

        return "redirect:/band/index";
    }

    @PostMapping("/band/change_password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes,
                                 Authentication auth) {

        Band band = bandRepository.findByUsername(auth.getName());

        if (!passwordEncoder.matches(currentPassword, band.getPassword())) {
            log.warn(auth.getName() + " entered wrong password to change his password");
            redirectAttributes.addFlashAttribute("error","You entered wrong password");
            return "redirect:../error";
        }

        band.setPassword(passwordEncoder.encode(newPassword));
        bandRepository.save(band);
        log.info(auth.getName() + " changed his password");

        redirectAttributes.addFlashAttribute("action", "password");

        return "redirect:/band/index";
    }

    @PostMapping("/band/edit_information")
    public String changeInformation(@RequestParam("name") String bandName,
                                    @RequestParam("date") String date,
                                    Authentication auth,
                                    RedirectAttributes redirectAttributes) {
        Band band = bandRepository.findByUsername(auth.getName());
        band.setBandName(bandName);
        band.setDate(LocalDate.parse(date));
        bandRepository.save(band);
        redirectAttributes.addFlashAttribute("action","editInfo");
        return "redirect:/band/index";
    }
}
