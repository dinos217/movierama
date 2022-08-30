package com.project.movierama.mappers;

import com.project.movierama.dtos.MovieDto;
import com.project.movierama.entities.Movie;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

    MovieDto MovieToMovieDto(Movie movie);
    Movie movieDtoToMovie(MovieDto movieDto);

}
