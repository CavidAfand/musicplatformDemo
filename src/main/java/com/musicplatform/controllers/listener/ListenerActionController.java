package com.musicplatform.controllers.listener;

import com.musicplatform.entities.Listener;
import com.musicplatform.entities.Music;
import com.musicplatform.repositories.ListenerRepository;
import com.musicplatform.repositories.MusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ListenerActionController {

    private ListenerRepository listenerRepository;
    private MusicRepository musicRepository;

    @Autowired
    public ListenerActionController(ListenerRepository listenerRepository,
                                    MusicRepository musicRepository) {
        this.listenerRepository = listenerRepository;
        this.musicRepository = musicRepository;
    }

    @PostMapping("/listener/like_music")
    public String likeMusic(@RequestParam("music") String musicPath,
                            Authentication auth,
                            RedirectAttributes redirectAttributes) {

        try {
            Listener listener = listenerRepository.findByUsername(auth.getName());
            Music music = musicRepository.findMusicByMusicPath(musicPath);

            listener.getLikedMusics().add(music);
            listenerRepository.save(listener);
            redirectAttributes.addFlashAttribute("action","like");
            return "redirect:/listener/liked_music";
        }
        catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error","Something went wrong");
            return "redirect:../error";
        }

    }

    @PostMapping("/listener/dislike_music")
    public String dislikeMusic(@RequestParam("music") String musicPath,
                               Authentication auth,
                               RedirectAttributes redirectAttributes) {
        try {
            Listener listener = listenerRepository.findByUsername(auth.getName());
            Music music = musicRepository.findMusicByMusicPath(musicPath);

            listener.getLikedMusics().remove(music);
            listenerRepository.save(listener);
            redirectAttributes.addFlashAttribute("action","dislike");
            return "redirect:/listener/liked_music";
        }
        catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong");
            return "redirect:../error";
        }
    }

    @GetMapping("/listener/search_music")
    public String searchDislikedSongs(@RequestParam("search") String search,
                                      @RequestParam(value = "id", required = false) Integer id,
                                      Authentication auth,
                                      Model model) {
        Listener listener = listenerRepository.findByUsername(auth.getName());
        List<Music> musicList = musicRepository.searchMusicByMusicName(search);

        List<String> likedMusicList = new LinkedList<>();
        listener.getLikedMusics().stream().forEach(i -> likedMusicList.add(i.getMusicPath()));
        Map<Music, Boolean> resultList = new HashMap<>();

        for (Music music: musicList) {
            boolean flag = false;
            for (String path: likedMusicList) {
                if (path.equals(music.getMusicPath()) == true) {
                    resultList.put(music, true);
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                resultList.put(music, false);
            }
        }

        model.addAttribute("resultList", resultList);
        model.addAttribute("user", listener);

        return "/listener/search_music";
    }



}
