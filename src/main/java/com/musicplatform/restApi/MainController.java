package com.musicplatform.restApi;

import com.musicplatform.entities.Musician;
import com.musicplatform.repositories.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    MusicianRepository musicianRepository;

    @GetMapping("/musician/restTest")
    public ResponseEntity getMusicians() {
        List<Musician> musicianList = musicianRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(musicianList);
    }

}
