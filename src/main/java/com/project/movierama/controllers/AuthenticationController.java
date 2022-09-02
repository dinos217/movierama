package com.project.movierama.controllers;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.services.AuthService;
import com.project.movierama.utils.MovieramaApiMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private AuthService authService;

    @Autowired
    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<MovieramaApiMessage> login(@RequestBody UserRequestDto userRequestDto) throws Exception {

        logger.info("Started authentication of user: " + userRequestDto.getUsername());

        try {
            return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(userRequestDto));
        } catch (BadCredentialsException e) {
            logger.info("ERROR: Bad login attempt.");
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "the f* dashboard mf";
    }
}
