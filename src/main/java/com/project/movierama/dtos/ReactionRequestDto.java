package com.project.movierama.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionRequestDto {

    private Long userId;
    private Long movieId;
    private Boolean like;
}
