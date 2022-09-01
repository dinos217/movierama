package com.project.movierama.services;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import com.project.movierama.utils.MovieramaApiMessage;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    MovieramaApiMessage authenticate(UserRequestDto userRequestDto) throws Exception;
}
