package com.project.movierama.services;

import com.project.movierama.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MovieramaUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public MovieramaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User: " + username + " not found."));
        return new MovieramaUserDetailsService(userRepository.findByUsername(username).get());
    }
}
