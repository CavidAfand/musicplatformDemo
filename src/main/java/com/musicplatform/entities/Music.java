package com.musicplatform.entities;


import com.musicplatform.entities.markers.Artist;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Data
@ToString
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "path", length = 70, nullable = false)
    private String musicPath;

    @Column(name="date", nullable = false)
    private LocalDate releaseDate;

//    @ManyToOne
//    private Artist artist;

    @ManyToOne
    private Musician musician;

    @ManyToOne
    private Band band;

    @ManyToMany
    @JoinTable(
            name="musics_and_genres",
            joinColumns = @JoinColumn(name="music_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<MusicGenre> genres;

    @ManyToMany(mappedBy = "likedMusics")
    private Set<Listener> listeners;
}
