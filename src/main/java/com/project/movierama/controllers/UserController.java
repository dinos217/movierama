package com.project.movierama.controllers;

import com.project.movierama.dtos.UserDto;
import com.project.movierama.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//        (value = "/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {

        logger.info("Started saving a new user...");
        return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(userDto));
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "the f* dashboard mf";
    }

    //https://youtu.be/1XnPLWJwiHM?t=4999

}
