package com.musicplatform.controllers.listener;

import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Musician;
import com.musicplatform.exceptions.NotImageException;
import com.musicplatform.repositories.ListenerRepository;
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
public class ListenerEditController {

    private ListenerRepository listenerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ListenerEditController(ListenerRepository listenerRepository, PasswordEncoder passwordEncoder) {
        this.listenerRepository = listenerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/listener/change_image")
    public String changeImage(@RequestParam("image") MultipartFile image, @RequestParam("original_name") String name,
                              Authentication auth,
                              RedirectAttributes redirectAttributes) {
        try {
            String newImageName = ImageUpload.checkAndChangeImage(image, auth.getName(), name);
            if (!newImageName.equals(name)) {
                Listener listener = listenerRepository.findByUsername(auth.getName());
                listener.setImagePath(newImageName);
                listenerRepository.save(listener);
            }
            log.info(auth.getName() + " has updated image successfully.");
        }
        catch  (NotImageException ex) {
            log.error(auth.getName() + " uploaded file is not image");
            redirectAttributes.addFlashAttribute("error","Your uploaded file is not image");
            return "redirect:../error";
        } catch (IOException ex) {
            log.error("Error has happened when " + auth.getName() + " uploaded image");
            redirectAttributes.addFlashAttribute("error","Error has happened. Please try to sign up");
            return "redirect:../error";
        }

        redirectAttributes.addFlashAttribute("action","photo");

        return "redirect:/listener/index";
    }

    @PostMapping("listener/change_password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 RedirectAttributes redirectAttributes,
                                 Authentication auth) {
        Listener listener = listenerRepository.findByUsername(auth.getName());


        if (!passwordEncoder.matches(currentPassword, listener.getPassword())) {
            log.warn(auth.getName() + " entered wrong password to change his password");
            redirectAttributes.addFlashAttribute("error","You entered wrong password");
            return "redirect:../error";
        }

        listener.setPassword(passwordEncoder.encode(newPassword));
        listenerRepository.save(listener);
        log.info(auth.getName() + " changed his password");

        redirectAttributes.addFlashAttribute("action", "password");

        return "redirect:/listener/index";
    }

    @PostMapping("listener/edit_information")
    public String editInformation(@RequestParam("name") String name,
                                  @RequestParam("surname") String surname,
                                  @RequestParam("date") String date,
                                  Authentication auth,
                                  RedirectAttributes redirectAttributes) {
        try {
            Listener listener = listenerRepository.findByUsername(auth.getName());
            listener.setName(name);
            listener.setSurname(surname);
            listener.setDate(LocalDate.parse(date));
            listenerRepository.save(listener);
            log.info(auth.getName() + " changed information");
            redirectAttributes.addFlashAttribute("action", "editInfo");
            return "redirect:/listener/index";
        }
        catch (Exception ex) {
            log.error("Error happened when " + auth.getName() + " edited information");
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return "redirect:../error";
        }
    }
}
