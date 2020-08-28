package com.musicplatform.controllers.band;

import com.musicplatform.entities.Band;
import com.musicplatform.entities.Music;
import com.musicplatform.entities.Post;
import com.musicplatform.repositories.BandRepository;
import com.musicplatform.repositories.MusicGenresRepository;
import com.musicplatform.repositories.MusicRepository;
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
public class BandPagesController {

    private BandRepository bandRepository;
    private MusicGenresRepository musicGenresRepository;
    private MusicRepository musicRepository;
    private PostRepository postRepository;

    @Autowired
    public BandPagesController(BandRepository bandRepository, MusicGenresRepository musicGenresRepository,
                               MusicRepository musicRepository, PostRepository postRepository) {
        this.bandRepository = bandRepository;
        this.musicGenresRepository = musicGenresRepository;
        this.musicRepository = musicRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/band/index")
    public String getBandIndex(Authentication auth, Model model) {
        Band band = bandRepository.findByUsername(auth.getName());
        List<String> genres =  musicGenresRepository.findAllGenres();

        model.addAttribute("user", band);
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser","band");
        return "/band/index";
    }

    @GetMapping("/band/music")
    public String getBandMusic(@RequestParam(value = "id", required = false) Integer id,
                               Authentication auth,
                               Model model) {
        Band band = bandRepository.findByUsername(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        List <Music> musicList = new LinkedList<>();

        if(id == null) id = 1;

        int size = band.getBandMusicList().size();
        int index = size - 1;
        if (size > 5) {
            for (int i = index - ((id - 1) * 5); i > index - (id * 5) && i >= 0; i--) {
                musicList.add(band.getBandMusicList().get(i));
            }
            model.addAttribute("musicList", musicList);
        }
        else {
            model.addAttribute("musicList", band.getBandMusicList());
        }

        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%5==0)?size/5:size/5+1);
        model.addAttribute("user", band);
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser", "band");
        return "band/music";
    }

    @GetMapping("/band/post")
    public String getBandPost(@RequestParam(value = "id", required = false) Integer id,
                              Authentication auth,
                              Model model) {
        Band band = bandRepository.findByUsername(auth.getName());
        List<String> genres = musicGenresRepository.findAllGenres();

        List<Post> postList = new LinkedList<>();

        if (id == null) id=1;

        int size = band.getPosts().size();
        int index = size - 1;

        if (size > 5) {
            for (int i = index - ((id - 1) * 5); i > index - (id * 5) && i >= 0; i--) {
                postList.add(band.getPosts().get(i));
            }
            model.addAttribute("postList", postList);
        }
        else {
            model.addAttribute("postList", band.getPosts());
        }

        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%5==0)?size/5:size/5+1);
        model.addAttribute("user", band);
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser", "band");
        return "/band/post";
    }

    @PostMapping("/band/search_music")
    public String searchBandMusic(@RequestParam("search") String name,
                                  Authentication auth,
                                  RedirectAttributes redirectAttributes) {
        Band band = bandRepository.findByUsername(auth.getName());
        List <Music> musicList = musicRepository.findAllByBandAndMusicNameContainingOrderByIdDesc(band, name);
        redirectAttributes.addFlashAttribute("search","true");
        redirectAttributes.addFlashAttribute("searchList", musicList);
        return "redirect:/band/music";
    }

    @PostMapping("/band/delete_music")
    public String deleteBandMusic(@RequestParam("music") String path,
                                  Authentication auth,
                                  RedirectAttributes redirectAttributes) {
        try {
            Band band = bandRepository.findByUsername(auth.getName());
            Music music = musicRepository.findMusicByMusicPath(path);
            band.getBandMusicList().remove(music);
            bandRepository.save(band);
            musicRepository.delete(music);
            log.info(auth.getName() + " delete music: " + music.getMusicName());
        }
        catch (Exception ex) {
            log.error("Error happened when " + auth.getName() + " deleted music");
            redirectAttributes.addFlashAttribute("error","Something went wrong. You couldn't delete music");
            return "redirect:../error";
        }

        return "redirect:/band/music";
    }

    @PostMapping("/band/delete_post")
    public String deleteBandPost(@RequestParam("post") String path,
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
        return "redirect:/band/post";
    }
}
