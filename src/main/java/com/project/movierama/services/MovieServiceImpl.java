package com.project.movierama.services;

import com.project.movierama.dtos.MovieRequestDto;
import com.project.movierama.dtos.MovieResponseDto;
import com.project.movierama.entities.Movie;
import com.project.movierama.entities.User;
import com.project.movierama.exceptions.BadRequestException;
import com.project.movierama.exceptions.InvalidRequestException;
import com.project.movierama.mappers.MovieMapper;
import com.project.movierama.repositories.MovieRepository;
import com.project.movierama.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private MovieMapper movieMapper = Mappers.getMapper(MovieMapper.class);

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MovieResponseDto save(MovieRequestDto movieDto) {

        if (movieRepository.existsByTitleAndReleaseYear(movieDto.getTitle(), movieDto.getReleaseYear())) {
            logger.info("Movie: " + movieDto.getTitle() + " has already been added.");
            throw new InvalidRequestException("Movie has already been added.");
        }

        User addedByUser = userRepository.findById(movieDto.getUserId())
                .orElseThrow(() -> new BadRequestException("User with id " + movieDto.getUserId() + " does not exist."));

        Movie movie = movieMapper.movieDtoToMovie(movieDto);
        movie.setUser(addedByUser);
        movie.setDateAdded(LocalDate.now());
        movieRepository.save(movie);

        return movieMapper.movieToMovieDto(movie);
    }

    @Override
    public Page<MovieResponseDto> findAll(Pageable pageable) {

        Page<Movie> moviesFromDb = movieRepository.findAll(pageable);

        return buildResponseListPaged(pageable, moviesFromDb);
    }

    @Override
    public Page<MovieResponseDto> findAllByUser(Pageable pageable, Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("User with id " + id + " does not exist."));

        Page<Movie> moviesFromDb = movieRepository.findAllByUser(user, pageable);

        return buildResponseListPaged(pageable, moviesFromDb);
    }

    private Page<MovieResponseDto> buildResponseListPaged(Pageable pageable, Page<Movie> moviesFromDb) {

        long total = 0L;
        if (!ObjectUtils.isEmpty(moviesFromDb)) total = moviesFromDb.stream().count();

        List<MovieResponseDto> movies = moviesFromDb.stream()
                .map(movie -> movieMapper.movieToMovieDto(movie))
                .toList();

        logger.info("Found all movies successfully.");
        return new PageImpl<>(movies, pageable, total);
    }
}
