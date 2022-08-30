package com.project.movierama.services;

import com.project.movierama.dtos.MovieDto;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    MovieDto save(MovieDto movieDto);
}
