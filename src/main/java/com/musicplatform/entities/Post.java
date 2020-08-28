package com.musicplatform.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    @Column(name="text")
    private String text;

    @Column(name="date")
    private LocalDate date;

    @Column(name = "imagePath")
    private String imagePath;

    @ManyToOne
    private Musician musician;

    @ManyToOne
    private Band band;

    @PrePersist
    protected void getCurrentDate() {
        this.date = LocalDate.now();
    }

    public Post(String text, String imagePath, Musician musician) {
        this.text = text;
        this.imagePath = imagePath;
        this.musician = musician;
    }

    public Post(String text, String imagePath, Band band) {
        this.text = text;
        this.imagePath = imagePath;
        this.band = band;
    }

}
