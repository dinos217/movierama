package com.project.movierama.services;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import com.project.movierama.mappers.UserMapper;
import com.project.movierama.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository,
                           AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponseDto authenticate(UserRequestDto userRequestDto) {

        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDto.getUsername(), userRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return buildUserResponseDto(userRequestDto);
    }

    private UserResponseDto buildUserResponseDto(UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(userRequestDto.getName());
        userResponseDto.setSurname(userRequestDto.getSurname());
        userResponseDto.setEmail(userRequestDto.getEmail());
        userResponseDto.setUsername(userRequestDto.getUsername());
        return userResponseDto;
    }
}
