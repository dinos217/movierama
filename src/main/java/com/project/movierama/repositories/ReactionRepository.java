package com.project.movierama.repositories;

import com.project.movierama.entities.Movie;
import com.project.movierama.entities.Reaction;
import com.project.movierama.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    Reaction findByUserAndMovie(User user, Movie movie);
}
