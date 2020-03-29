package com.musicplatform.entities;

import com.musicplatform.entities.markers.Artist;
import com.musicplatform.entities.markers.PlatformUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Band implements Artist, PlatformUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="band_name", length = 70, nullable = false)
    private String bandName;

    @Column(name="email", length = 100, nullable = false)
    private String email;

    @Column(name="username", length = 102, nullable = false)
    private String username;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @Column(name="image_path", length = 100, nullable = false)
    private String imagePath;

    @OneToMany
    private List<Music> bandMusicList;

    @ManyToMany(mappedBy = "bands")
    private Set<Listener> listeners;

    @Transient
    public static final String authority = "BAND";

    public Band(String bandName, String email, String username, String password, LocalDate date, String imagePath) {
        this.bandName = bandName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.date = date;
        this.imagePath = imagePath;
    }
}
