package com.project.movierama.mappers;

import com.project.movierama.dtos.ReactionDto;
import com.project.movierama.entities.Reaction;
import org.mapstruct.Mapper;

@Mapper
public interface ReactionMapper {

    ReactionDto reactionToReactionDto(Reaction reaction);
}
