package com.musicplatform.controllers.listener;

import com.musicplatform.entities.*;
import com.musicplatform.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
@SessionAttributes("user")
public class ListenerPagesController {

    private ListenerRepository listenerRepository;
    private MusicGenresRepository musicGenresRepository;
    private MusicRepository musicRepository;
    private PostRepository postRepository;
    private BandRepository bandRepository;
    private MusicianRepository musicianRepository;
    private int elementPerPage = 10;

    @Autowired
    public ListenerPagesController(ListenerRepository listenerRepository,
                                   MusicGenresRepository musicGenresRepository,
                                   MusicRepository musicRepository,
                                   PostRepository postRepository,
                                   BandRepository bandRepository,
                                   MusicianRepository musicianRepository) {
        this.listenerRepository = listenerRepository;
        this.musicGenresRepository = musicGenresRepository;
        this.musicRepository = musicRepository;
        this.postRepository = postRepository;
        this.bandRepository = bandRepository;
        this.musicianRepository = musicianRepository;
    }

    @GetMapping("/listener/index")
    public String getListenerIndex(Authentication auth, Model model) {

        List<String> genres =  musicGenresRepository.findAllGenres();

        model.addAttribute("user", listenerRepository.findByUsername(auth.getName()));
        model.addAttribute("genres", genres);
        model.addAttribute("platformUser", "listener");
        return "/listener/index";
    }

    @GetMapping("/listener/all_music")
    public String getMusicList(@RequestParam(value = "id",required = false) Integer id,
                               Authentication auth,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (id == null) id = 1;

        PageRequest page = PageRequest.of(id-1, elementPerPage);

        Listener listener = listenerRepository.findByUsername(auth.getName());

        List <String> paths = new LinkedList<>();
        paths.add("");
        listener.getLikedMusics().stream().forEach(i -> paths.add(i.getMusicPath()));

        /* get songs which listener hasn't liked yet */
        List<Music> musicList = musicRepository.findAllByMusicPathIsNotInOrderByIdDesc(page, paths).getContent();

        /* if music list is empty, it goes to error page.*/
        if (musicList.size() == 0) {
            redirectAttributes.addFlashAttribute("error", "Page not found");
            return "redirect:../error";
        }

        long size = musicRepository.count() - listener.getLikedMusics().size();

        List<String> genres =  musicGenresRepository.findAllGenres();

        model.addAttribute("musicList", musicList);
        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%elementPerPage==0)?size/elementPerPage:size/elementPerPage+1);
        model.addAttribute("user", listener);
        model.addAttribute("genres", genres);
        return "/listener/all_music";
    }

    @GetMapping("/listener/liked_music")
    public String getLikedMusic(@RequestParam(value = "id", required = false) Integer id,
                                Authentication auth,
                                Model model) {

        if (id == null) id = 1;

        Listener listener = listenerRepository.findByUsername(auth.getName());

        List<Music> likedSongs = new LinkedList<>(listener.getLikedMusics());
        List<Music> musicList = new LinkedList<>();

        int size = listener.getLikedMusics().size();
        int index = size - 1;
        if (size > elementPerPage) {
            for (int i = index - ((id - 1) * elementPerPage); i > index - (id * elementPerPage) && i >= 0; i--) {
                musicList.add(likedSongs.get(i));
            }
            model.addAttribute("musicList", musicList);
        }
        else {
            model.addAttribute("musicList", likedSongs);
        }

        List<String> genres =  musicGenresRepository.findAllGenres();

        model.addAttribute("user", listener);
        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%elementPerPage==0)?size/elementPerPage:size/elementPerPage+1);
        model.addAttribute("genres", genres);
        return "/listener/liked_music";

    }

    @GetMapping("/listener/musicians")
    public String getMusicians(@RequestParam(value = "id", required = false) Integer id,
                               Authentication auth,
                               Model model) {
        if (id == null) id = 1;

        List<String> genres = musicGenresRepository.findAllGenres();

        Listener listener = listenerRepository.findByUsername(auth.getName());

        List<String> usernameList = new LinkedList<>();
        usernameList.add("");

        listener.getMusicians().stream().forEach(i -> usernameList.add(i.getUsername()));

        List<Musician> musicians = musicianRepository.findAllByUsernameIsNotInOrderByIdDesc(PageRequest.of(id-1,elementPerPage),usernameList).getContent();

        long size = musicianRepository.count();

        model.addAttribute("genres", genres);
        model.addAttribute("musicians", musicians);
        model.addAttribute("user", listener);
        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%elementPerPage==0)?size/elementPerPage:size/elementPerPage+1);

        return "/listener/musicians";
    }

    @GetMapping("/listener/musician/{musician}/songs")
    public String getMusicianSongs(@PathVariable("musician") String musicianUsername,
                                   @RequestParam(value = "id", required = false) Integer id,
                                   Model model,
                                   Authentication auth) {

        if (id == null) id = 1;

        Listener listener = listenerRepository.findByUsername(auth.getName());
        Musician musician = musicianRepository.findByUsername(musicianUsername);

        List <Music> musicList = new LinkedList<>();

        int size = musician.getMusicianMusicList().size();
        int index = size - 1;
        if (size > elementPerPage) {
            for (int i = index - ((id - 1) * elementPerPage); i > index - (id * elementPerPage) && i >= 0; i--) {
                musicList.add(musician.getMusicianMusicList().get(i));
            }
            model.addAttribute("musicList", musicList);
        }
        else {
            musician.getMusicianMusicList().stream().forEach(i -> musicList.add(i));
            model.addAttribute("musicList", musicList);
        }

        List<String> genres =  musicGenresRepository.findAllGenres();

        model.addAttribute("page", id);
        model.addAttribute("pageNumber", (size%elementPerPage==0)?size/elementPerPage:size/elementPerPage+1);
        model.addAttribute("user", listener);
        model.addAttribute("musician", musician);
        model.addAttribute("genres", genres);
        model.addAttribute("musicList", musicList);

        return "/listener/musician_songs";
    }

}
