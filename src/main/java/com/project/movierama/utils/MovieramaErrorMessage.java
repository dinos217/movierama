package com.project.movierama.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class MovieramaErrorMessage {

    private HttpStatus status;
    private int statusCode;
    private String message;
}
