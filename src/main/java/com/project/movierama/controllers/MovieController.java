package com.project.movierama.controllers;

import com.project.movierama.dtos.MovieRequestDto;
import com.project.movierama.dtos.MovieResponseDto;
import com.project.movierama.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieResponseDto> save(@RequestBody MovieRequestDto movieRequestDto) {

        logger.info("Started saving a new movie...");
        return ResponseEntity.status(HttpStatus.OK).body(movieService.save(movieRequestDto));
    }

    @GetMapping(value = "/all")
    Page<MovieResponseDto> findAll(@RequestParam Integer page, @RequestParam Integer pageSize,
                                   @RequestParam String sortBy, @RequestParam String direction) {

        logger.info("Started finding all movies paged...");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return movieService.findAll(pageable);
    }

    @GetMapping(value = "/added-by-user/{userId}")
    Page<MovieResponseDto> findAllByUser(@PathVariable Long userId,
                                         @RequestParam Integer page, @RequestParam Integer pageSize,
                                         @RequestParam String sortBy, @RequestParam String direction) {

        logger.info("Started finding all movies added by user: " + userId);

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return movieService.findAllByUser(pageable, userId);
    }
}
