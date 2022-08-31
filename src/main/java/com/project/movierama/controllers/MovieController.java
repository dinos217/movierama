package com.project.movierama.controllers;

import com.project.movierama.dtos.MovieResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/movie")
public class MovieController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MovieResponseDto> save(@RequestBody MovieResponseDto movieDto) {

        return null;
    }



}
