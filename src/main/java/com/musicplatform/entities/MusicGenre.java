package com.musicplatform.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
//@ToString
public class MusicGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="genre",length = 70, nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private Set<Music> musics;

}
