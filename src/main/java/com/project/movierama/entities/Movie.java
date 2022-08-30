package com.project.movierama.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    //https://dev.to/xrio/getting-started-with-spring-boot-creating-a-simple-movies-list-api-41l6

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private Short releaseYear;

    @Column(name = "plot")
    private String plot;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "addedBy")
    private String addedByUsername;

    @ToString.Exclude
    @ManyToOne
    private User addedBy;

}
