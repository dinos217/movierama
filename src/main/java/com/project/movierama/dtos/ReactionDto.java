package com.project.movierama.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionDto {

    private String username;
    private String movie;
    private String reaction;
}
