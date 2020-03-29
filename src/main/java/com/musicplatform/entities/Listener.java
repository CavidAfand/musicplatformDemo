package com.musicplatform.entities;

import com.musicplatform.entities.markers.Artist;
import com.musicplatform.entities.markers.PlatformUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@ToString
@NoArgsConstructor
public class Listener implements PlatformUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name",length = 70, nullable = false)
    private String name;

    @Column(name="surname", length = 70, nullable = false)
    private String surname;

    @Column(name="email", length = 100, nullable = false)
    private String email;

    @Column(name="username", length = 102, nullable = false)
    private String username;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name="gender",length = 10, nullable = false)
    private String gender;

    @Column(name="image_path", length = 100)
    private String imagePath;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "listener_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id")
    )
    private Set<Music> likedMusics;

    @ManyToMany
    @JoinTable (
            joinColumns = @JoinColumn(name="listener_id"),
            inverseJoinColumns = @JoinColumn(name = "musician_id")
    )
    private Set<Musician> musicians;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name="listener_id"),
            inverseJoinColumns = @JoinColumn(name="band_id")
    )
    private Set<Band> bands;

    @Transient
    public static final  String authority = "LISTENER";

    public Listener(String name, String surname, String email, String username, String password, LocalDate date, String gender, String imagePath) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.imagePath = imagePath;
    }


    //    @ManyToMany
//    @OneToOne
//    @JoinTable (
//            name = "listeners_artists",
//            joinColumns = @JoinColumn(name = "id"),
//            inverseJoinColumns = {
//                    @JoinColumn(
//                            table = "musician",
//                            name = "id"
//                    ),
//                    @JoinColumn(
//                            table = "band",
//                            name="id"
//                    )
//            }
//    )
//    private Set<Artist> artists;
}
