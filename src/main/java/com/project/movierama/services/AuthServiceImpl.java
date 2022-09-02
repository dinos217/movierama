package com.project.movierama.services;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.mappers.UserMapper;
import com.project.movierama.repositories.UserRepository;
import com.project.movierama.utils.MovieramaApiMessage;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public MovieramaApiMessage authenticate(UserRequestDto userRequestDto) {

        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequestDto.getUsername(), userRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("SUCCESS: User '" + userRequestDto.getUsername() + "' logged in successfully");

        return getMovieramaApiMessage();
    }

    private MovieramaApiMessage getMovieramaApiMessage() {
        MovieramaApiMessage apiMessage = new MovieramaApiMessage();
        apiMessage.setStatus(HttpStatus.OK);
        apiMessage.setStatusCode(HttpStatus.OK.value());
        apiMessage.setMessage("Log in successful.");
        return apiMessage;
    }
}
