package com.project.movierama.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieResponseDto {

    private Long id;
    private String title;
    private Short releaseYear;
    private String plot;
}
