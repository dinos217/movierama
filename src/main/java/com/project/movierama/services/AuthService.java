package com.project.movierama.services;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    UserResponseDto authenticate(UserRequestDto userRequestDto) throws Exception;
}
