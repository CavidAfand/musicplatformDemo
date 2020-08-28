package com.musicplatform.controllers.band;

import com.musicplatform.entities.*;
import com.musicplatform.exceptions.NotImageException;
import com.musicplatform.exceptions.NotMusicException;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.MusicGenresRepository;
import com.musicplatform.repositories.MusicRepository;
import com.musicplatform.repositories.PostRepository;
import com.musicplatform.services.fileServices.ImageUpload;
import com.musicplatform.services.fileServices.MusicUpload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Set;

@Slf4j
@Controller
public class BandUploadController {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private MusicGenresRepository musicGenresRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/band/upload_music")
    public String uploadMusic(@RequestParam("name") String name,
                              @RequestParam("information") String information,
                              @RequestParam("musicFile") MultipartFile musicFile,
                              @RequestParam("genre") Set<String> genres,
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

        Band band = bandRepository.findByUsername(auth.getName());

        Music music = new Music(name, information, musicPath, band, genreList);

        musicRepository.save(music);
        band.getBandMusicList().add(music);
        bandRepository.save(band);


        log.info(auth.getName() + " uploaded music: " + music.getMusicPath());
        redirectAttributes.addFlashAttribute("action","music");
        return "redirect:/band/index";
    }

    @PostMapping("/band/upload_post")
    public String uploadPost(@RequestParam("text") String text,
                             @RequestParam("image") MultipartFile image,
                             Authentication auth,
                             RedirectAttributes redirectAttributes) {



        if (text == null && ImageUpload.checkImage(image) == false) {
            redirectAttributes.addFlashAttribute("error","Your post is empty");
            return "redirect:../error";
        }

        String imagePath = null;

        if (ImageUpload.checkImage(image) != false) {
            try {
                imagePath = ImageUpload.checkAndSavePostImage(image, auth.getName());
            }
            catch (NotImageException ex) {
                log.error(auth.getName() + " has uploaded file is not image");
                redirectAttributes.addFlashAttribute("error","Your uploaded file is not image");
                return "redirect:../error";
            }
            catch (IOException ex) {
                log.error(auth.getName() + " has uploaded file, but something went wrong");
                redirectAttributes.addFlashAttribute("error","Something went wrong");
                return "redirect:../error";
            }
        }

        Band band = bandRepository.findByUsername(auth.getName());
        Post post = new Post(text, imagePath, band);
        postRepository.save(post);
        band.getPosts().add(post);
        bandRepository.save(band);

        log.info(auth.getName() + " has created new post");
        redirectAttributes.addFlashAttribute("action", "post");
        return "redirect:/band/index";
    }
}
