package com.project.movierama.services;

import com.project.movierama.dtos.MovieDto;
import com.project.movierama.entities.Movie;
import com.project.movierama.exceptions.InvalidRequestException;
import com.project.movierama.mappers.MovieMapper;
import com.project.movierama.repositories.MovieRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MovieRepository movieRepository;
    private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    @Override
    public MovieDto save(MovieDto movieDto) {

        if (movieRepository.existsByTitleAndReleaseYear(movieDto.getTitle(), movieDto.getReleaseYear())) {
            throw new InvalidRequestException("Movie is already added by another user.");
        }

        Movie movie = movieRepository.save(movieMapper.movieDtoToMovie(movieDto));

        return movieMapper.MovieToMovieDto(movie);
    }
}
