package com.project.movierama.exceptions;

import com.project.movierama.utils.MovieramaApiMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MovieramaExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ResponseEntity<MovieramaApiMessage> processValidationError(InvalidRequestException e) {

        logger.info("ERROR --> " + e.getMessage());

        MovieramaApiMessage response = buildMovieramaErrorMessage(e, HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<MovieramaApiMessage> processValidationError(BadRequestException e) {

        logger.info("ERROR --> " + e.getMessage());

        MovieramaApiMessage response = buildMovieramaErrorMessage(e, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private MovieramaApiMessage buildMovieramaErrorMessage(RuntimeException e, HttpStatus status) {
        MovieramaApiMessage response = new MovieramaApiMessage();
        response.setStatus(status);
        response.setStatusCode(status.value());
        response.setMessage(e.getMessage());
        return response;
    }
}
