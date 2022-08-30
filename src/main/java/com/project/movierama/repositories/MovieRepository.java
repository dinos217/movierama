package com.project.movierama.repositories;

import com.project.movierama.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByTitleAndReleaseYear(String title, Short releaseYear);

    Boolean existsByTitleAndReleaseYear(String title, Short releaseYear);

    List<Movie> findAllByUser(Long id);

}
