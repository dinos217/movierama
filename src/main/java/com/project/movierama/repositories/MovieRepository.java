package com.project.movierama.repositories;

import com.project.movierama.entities.Movie;
import com.project.movierama.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Boolean existsByTitleAndReleaseYear(String title, Short releaseYear);

    Page<Movie> findAllByUser(User user, Pageable pageable);

}
