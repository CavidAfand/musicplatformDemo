package com.musicplatform.entities;


import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "musicName")
    private String musicName;

    @Column(name = "path", length = 70, nullable = false)
    private String musicPath;

    @Column(name="date", nullable = false)
    private LocalDate releaseDate;

    @Lob
    @Column(name="information")
    private String information;

    @ManyToOne
    private Musician musician;

    @ManyToOne
    private Band band;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="musics_and_genres",
            joinColumns = @JoinColumn(name="music_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    private Set<MusicGenre> genres;

    @ManyToMany(mappedBy = "likedMusics")
    private Set<Listener> listeners;

    @PrePersist
    protected void getCurrentDate() {
        this.releaseDate = LocalDate.now();
    }

    public Music(String musicName, String information, String musicPath, Musician musician, Set<MusicGenre> genres) {
        this.musicName = musicName;
        this.information = information;
        this.musicPath = musicPath;
        this.musician = musician;
        this.genres = genres;
    }

    public Music(String musicName, String information, String musicPath, Band band, Set<MusicGenre> genres) {
        this.musicName = musicName;
        this.information = information;
        this.musicPath = musicPath;
        this.band = band;
        this.genres = genres;
    }
}
