package com.musicplatform.controllers.musician;

import com.musicplatform.entities.Musician;
import com.musicplatform.repositories.MusicGenresRepository;
import com.musicplatform.repositories.MusicianRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class MusicianHomeController {

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    MusicGenresRepository musicGenresRepository;

    @GetMapping("/musician/index")
    public String getMusicianIndex(Authentication auth, Model model) {

        Musician musician = getMusician(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        model.addAttribute("user", musician);
        model.addAttribute("genres",genres);
        model.addAttribute("platformUser", "musician");

        return "musician/index";
    }

    private Musician getMusician(String username) {
        return musicianRepository.findByUsername(username);
    }

}
