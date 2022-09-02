package com.project.movierama.services;

import com.project.movierama.dtos.LoginRequestDto;
import com.project.movierama.utils.MovieramaApiMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public MovieramaApiMessage authenticate(LoginRequestDto loginRequestDto) {

        Authentication authentication;
        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        logger.info("SUCCESS: User '" + loginRequestDto.getUsername() + "' logged in successfully");

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
