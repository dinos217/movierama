package com.project.movierama.mappers;

import com.project.movierama.dtos.MovieRequestDto;
import com.project.movierama.dtos.MovieResponseDto;
import com.project.movierama.entities.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    MovieResponseDto MovieToMovieDto(Movie movie);
    Movie movieDtoToMovie(MovieRequestDto movieDto);

}
