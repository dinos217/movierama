package com.project.movierama.services;

import com.project.movierama.dtos.ReactionDto;
import com.project.movierama.dtos.ReactionRequestDto;
import com.project.movierama.entities.Movie;
import com.project.movierama.entities.Reaction;
import com.project.movierama.entities.User;
import com.project.movierama.enums.UserReaction;
import com.project.movierama.exceptions.BadRequestException;
import com.project.movierama.exceptions.InvalidRequestException;
import com.project.movierama.mappers.ReactionMapper;
import com.project.movierama.repositories.MovieRepository;
import com.project.movierama.repositories.ReactionRepository;
import com.project.movierama.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionServiceImpl implements ReactionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ReactionMapper reactionMapper = Mappers.getMapper(ReactionMapper.class);

    private UserRepository userRepository;
    private MovieRepository movieRepository;
    private ReactionRepository reactionRepository;

    @Autowired
    public ReactionServiceImpl(UserRepository userRepository, MovieRepository movieRepository,
                               ReactionRepository reactionRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.reactionRepository = reactionRepository;
    }

    @Override
    public ReactionDto save(ReactionRequestDto reactionRequestDto) {

        User user = userRepository.findById(reactionRequestDto.getUserId())
                .orElseThrow(() -> new BadRequestException("User with id " + reactionRequestDto.getUserId() + " does not exist."));

        Movie movie = movieRepository.findById(reactionRequestDto.getMovieId())
                .orElseThrow(() -> new BadRequestException("Movie with id " + reactionRequestDto.getMovieId() + " does not exist."));

        if (movie.getUser().equals(user))
            throw new BadRequestException("Users cannot vote for movies they have added.");

        if (!reactionRepository.existsByUserAndMovie(user, movie)) {
            Reaction reaction = new Reaction();
            reaction.setUser(user);
            reaction.setMovie(movie);
            reaction.setLike(reactionRequestDto.getLike());

            return buildReactionDto(reactionRepository.save(reaction));
        } else {
            throw new InvalidRequestException("User " + user.getUsername() + " has already voted for this movie.");
        }
    }

    @Override
    public ReactionDto update(ReactionRequestDto reactionRequestDto) {

        User user = userRepository.findById(reactionRequestDto.getUserId())
                .orElseThrow(() -> new BadRequestException("User with id " + reactionRequestDto.getUserId() + " does not exist."));

        Movie movie = movieRepository.findById(reactionRequestDto.getMovieId())
                .orElseThrow(() -> new BadRequestException("Movie with id " + reactionRequestDto.getMovieId() + " does not exist."));

        Reaction reaction = reactionRepository.findByUserAndMovie(user, movie);
//        reaction.setLike(!reaction.getLike());
        reaction.setLike(reactionRequestDto.getLike());

        return buildReactionDto(reactionRepository.save(reaction));
    }

    private ReactionDto buildReactionDto(Reaction reaction) {
        ReactionDto reactionDto = new ReactionDto();
        reactionDto.setUsername(reaction.getUser().getUsername());
        reactionDto.setMovie(reaction.getMovie().getTitle());
        if (reaction.getLike()) {
            reactionDto.setReaction(UserReaction.LIKE.name());
        } else {
            reactionDto.setReaction(UserReaction.HATE.name());
        }

        return reactionDto;
    }
}
