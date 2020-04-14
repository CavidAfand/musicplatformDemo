package com.musicplatform.controllers.musician;

import com.musicplatform.entities.Music;
import com.musicplatform.entities.MusicGenre;
import com.musicplatform.entities.Musician;
import com.musicplatform.exceptions.NotMusicException;
import com.musicplatform.repositories.MusicGenresRepository;
import com.musicplatform.repositories.MusicRepository;
import com.musicplatform.repositories.MusicianRepository;
import com.musicplatform.services.fileServices.MusicUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class MusicianUploadController {

    @Autowired
    MusicGenresRepository musicGenresRepository;

    @Autowired
    MusicianRepository musicianRepository;

    @Autowired
    MusicRepository musicRepository;

    @PostMapping("/musician/upload_music")
    public String uploadMusic(@RequestParam("name") String name,
                              @RequestParam("information") String information,
                              @RequestParam("musicFile") MultipartFile musicFile,
                              @RequestParam("genre")Set<String> genres,
                              Authentication auth,
                              RedirectAttributes redirectAttributes) {

        Set<MusicGenre> genreList = musicGenresRepository.findAllByGenreIn(genres);

        String musicPath = null;

        try {
            musicPath = MusicUpload.checkAndUploadMusic(musicFile, auth.getName());
        } catch (NotMusicException e) {
            redirectAttributes.addFlashAttribute("error","Your uploaded file is not music");
            return "redirect:../error";
        }

        Musician musician = musicianRepository.findByUsername(auth.getName());

        Music music = new Music(name, information, musicPath, musician, genreList);

        musicRepository.save(music);
        musician.getMusicianMusicList().add(music);
        musicianRepository.save(musician);


        log.info("music uploaded");
        redirectAttributes.addFlashAttribute("action","music");
        return "redirect:/musician/index";
    }

    @PostMapping("/musician/upload_post")
    public String uploadPost(@RequestParam("text") String text,
                             @RequestParam("image") MultipartFile image,
                             Authentication auth,
                             RedirectAttributes redirectAttributes) {

        if (text == null && image ==null) {
            redirectAttributes.addFlashAttribute("error","Your post is empty");
            return "redirect:../error";
        }

        String imagePath = null;

        if (image != null) {

        }

        return "/musician/index";
    }

}
