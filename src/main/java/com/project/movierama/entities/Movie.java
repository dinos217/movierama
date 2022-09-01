package com.project.movierama.entities;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @ToString.Exclude
    @ManyToOne
    private User user;

}
