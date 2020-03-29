package com.musicplatform.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class MusicGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="genre",length = 70, nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private Set<Music> musics;

}
