package com.project.movierama.services;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import com.project.movierama.entities.User;
import com.project.movierama.exceptions.InvalidRequestException;
import com.project.movierama.mappers.UserMapper;
import com.project.movierama.repositories.UserRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto registerUser(UserRequestDto userDto) {

        if (userRepository.existsByUsernameOrEmail(userDto.getUsername(), userDto.getEmail())) {
            logger.info("Username: " + userDto.getUsername() + " or email: " + userDto.getEmail() + " already exists.");
            throw new InvalidRequestException("Given username or email already exists.");
        }

        User user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        logger.info("SUCCESS: User with username: " + userDto.getUsername() + " registered successfully!");

        return userMapper.userToUserDto(userRepository.save(user));
    }
}
