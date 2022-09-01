package com.project.movierama.controllers;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import com.project.movierama.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto) {

        logger.info("Started saving a new user...");
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userRequestDto));
    }


}
