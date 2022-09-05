package com.project.movierama.controllers;

import com.project.movierama.dtos.MovieResponseDto;
import com.project.movierama.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MovieService movieService;

    @Autowired
    public HomeController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    Page<MovieResponseDto> findAllMovies(@RequestParam Integer page, @RequestParam Integer pageSize,
                                         @RequestParam String sortBy, @RequestParam String direction) {

        logger.info("Started finding all movies paged...");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return movieService.findAll(pageable);
    }
}
