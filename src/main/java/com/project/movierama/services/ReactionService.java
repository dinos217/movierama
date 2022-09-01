package com.project.movierama.services;

import com.project.movierama.dtos.ReactionDto;
import com.project.movierama.dtos.ReactionRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface ReactionService {

    ReactionDto save(ReactionRequestDto reactionRequestDto);

    ReactionDto update(ReactionRequestDto reactionRequestDto);
}
