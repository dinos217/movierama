package com.project.movierama.controllers;

import com.project.movierama.dtos.ReactionDto;
import com.project.movierama.dtos.ReactionRequestDto;
import com.project.movierama.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "reactions")
public class ReactionController {

    private ReactionService reactionService;

    @Autowired
    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ReactionDto> save(@RequestBody ReactionRequestDto reactionRequestDto) {

        return ResponseEntity.status(HttpStatus.OK).body(reactionService.save(reactionRequestDto));
    }

    @PutMapping
    public ResponseEntity<ReactionDto> update(@RequestBody ReactionRequestDto reactionRequestDto) {

        return ResponseEntity.status(HttpStatus.OK).body(reactionService.update(reactionRequestDto));
    }
}
