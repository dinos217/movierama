package com.project.movierama.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {

    private String title;
    private Short releaseYear;
    private String plot;
    private Long duration;
}
