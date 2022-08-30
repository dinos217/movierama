package com.project.movierama.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieDto {

    private Long id;
    private String title;
    private Short releaseYear;
    private String plot;
    private Long duration;
}
