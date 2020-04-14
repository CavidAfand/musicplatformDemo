package com.musicplatform.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@ToString
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

}
