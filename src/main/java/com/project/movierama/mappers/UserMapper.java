package com.project.movierama.mappers;

import com.project.movierama.dtos.UserRequestDto;
import com.project.movierama.dtos.UserResponseDto;
import com.project.movierama.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserResponseDto userToUserDto(User user);
    User userDtoToUser(UserRequestDto userDto);
}
