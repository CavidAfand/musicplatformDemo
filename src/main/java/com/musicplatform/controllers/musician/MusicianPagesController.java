package com.musicplatform.controllers.musician;

import com.musicplatform.entities.Music;
import com.musicplatform.entities.Musician;
import com.musicplatform.entities.Post;
import com.musicplatform.repositories.MusicGenresRepository;
import com.musicplatform.repositories.MusicRepository;
import com.musicplatform.repositories.MusicianRepository;
import com.musicplatform.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
public class MusicianPagesController {

    @Autowired
    private MusicianRepository musicianRepository;

    @Autowired
    private MusicGenresRepository musicGenresRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/musician/index")
    public String getMusicianIndex(Authentication auth, Model model) {

        Musician musician = getMusician(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        model.addAttribute("user", musician);
        model.addAttribute("genres",genres);
        model.addAttribute("platformUser", "musician");

        return "musician/index";
    }

    @GetMapping("/musician/music")
    public String getMusicianMusic(@RequestParam(value = "id", required = false) Integer id,
            Authentication auth,
            Model model) {
        Musician musician = getMusician(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        List <Music> musicList = new LinkedList<>();

        if(id == null) id = 1;

        int size = musician.getMusicianMusicList().size();
        int index = size - 1;
        if (size > 5) {
            for (int i = index - ((id - 1) * 5); i > index - (id * 5) && i >= 0; i--) {
                musicList.add(musician.getMusicianMusicList().get(i));
            }
            model.addAttribute("musicList", musicList);
        }
        else {
            model.addAttribute("musicList", musician.getMusicianMusicList());
        }

        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%5==0)?size/5:size/5+1);
        model.addAttribute("user", musician);
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser", "musician");
        return "musician/music";
    }

    @GetMapping("/musician/post")
    public String getMusicianPost(@RequestParam(value = "id", required = false) Integer id,
                                  Authentication auth,
                                      Model model) {
        Musician musician = getMusician(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        List<Post> postList = new LinkedList<>();

        if (id == null) id=1;

        int size = musician.getPosts().size();
        int index = size - 1;

        if (size > 5) {
            for (int i = index - ((id - 1) * 5); i > index - (id * 5) && i >= 0; i--) {
                postList.add(musician.getPosts().get(i));
            }
            model.addAttribute("postList", postList);
        }
        else {
            model.addAttribute("postList", musician.getPosts());
        }

        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%5==0)?size/5:size/5+1);
        model.addAttribute("user", musician);
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser", "musician");
        return "/musician/post";
    }

    @PostMapping("/musician/search_music")
    public String searchMusicianMusic(@RequestParam("search") String name,
                                      Authentication auth,
                                      RedirectAttributes redirectAttributes) {

        Musician musician = musicianRepository.findByUsername(auth.getName());
        List <Music> musicList = musicRepository.findAllByMusicianAndMusicNameContainingOrderByIdDesc(musician, name);
        redirectAttributes.addFlashAttribute("search","true");
        redirectAttributes.addFlashAttribute("searchList", musicList);
        return "redirect:/musician/music";
    }

    @PostMapping("/musician/delete_music")
    public String deleteMusicianMusic(@RequestParam("music") String path,
                                      Authentication auth,
                                      RedirectAttributes redirectAttributes) {
        try {
            Musician musician = musicianRepository.findByUsername(auth.getName());
            Music music = musicRepository.findMusicByMusicPath(path);
            musician.getMusicianMusicList().remove(music);
            musicianRepository.save(musician);
            musicRepository.delete(music);
            log.info(auth.getName() + " delete music: " + music.getMusicName());
        }
        catch (Exception ex) {
            log.error("Error happened when " + auth.getName() + " deleted music");
            redirectAttributes.addFlashAttribute("error","Something went wrong. You couldn't delete music");
            return "redirect:../error";
        }

        return "redirect:/musician/music";
    }

    @PostMapping("/musician/delete_post")
    public String deleteMusicianPost(@RequestParam("post") String path,
                                     Authentication auth,
                                     RedirectAttributes redirectAttributes) {
        try {
            Post post = postRepository.findPostByImagePath(path);
            postRepository.delete(post);
        }
        catch (Exception ex) {
            log.error("Error happened when " + auth.getName() + " deleted post");
            redirectAttributes.addFlashAttribute("error","Something went wrong. You couldn't delete post");
            return "redirect:../error";
        }
        return "redirect:/musician/post";
    }

    private Musician getMusician(String username) {
        return musicianRepository.findByUsername(username);
    }

}
