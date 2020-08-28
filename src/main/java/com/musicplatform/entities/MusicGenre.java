package com.musicplatform.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class MusicGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="genre",length = 70, nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private Set<Music> musics;

}
