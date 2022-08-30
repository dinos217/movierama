package com.project.movierama.services;

import com.project.movierama.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto registerUser(UserDto userDto);

    UserDto findUser(UserDto userDto);
}
