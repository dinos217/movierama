package com.project.movierama.services;

import com.project.movierama.dtos.MovieRequestDto;
import com.project.movierama.dtos.MovieResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {

    MovieResponseDto save(MovieRequestDto movieDto);

    Page<MovieResponseDto> findAll(Pageable pageable);

    Page<MovieResponseDto> findAllByUser(Pageable pageable, Long id);
}
