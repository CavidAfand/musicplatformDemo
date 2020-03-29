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
public class Musician implements Artist, PlatformUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", length = 70, nullable = false)
    private String name;

    @Column(name="surname", length = 70, nullable = false)
    private String surname;

    @Column(name="email", length = 100, nullable = false)
    private String email;

    @Column(name="username", length = 102, nullable = false)
    private String username;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name="nick_name", length = 70)
    private String nickName;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name="gender", length = 10, nullable = false)
    private String gender;

    @Column(name="image_path", length = 100, nullable = false)
    private String imagePath;

    @OneToMany
    private List<Music> musicianMusicList;

    @ManyToMany(mappedBy = "musicians")
    private Set<Listener> listeners;

    @Transient
    public static final String authority = "MUSICIAN";

    public Musician(String name, String surname, String email, String username, String password, String nickName, LocalDate date, String gender, String imagePath) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.date = date;
        this.gender = gender;
        this.imagePath = imagePath;
    }

}
