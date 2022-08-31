package com.project.movierama.services;

import com.project.movierama.dtos.MovieRequestDto;
import com.project.movierama.dtos.MovieResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    MovieResponseDto save(MovieRequestDto movieDto);
}
