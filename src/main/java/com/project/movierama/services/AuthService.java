package com.project.movierama.services;

import com.project.movierama.dtos.LoginRequestDto;
import com.project.movierama.utils.MovieramaApiMessage;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    MovieramaApiMessage authenticate(LoginRequestDto loginRequestDto) throws Exception;
}
