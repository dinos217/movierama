package com.project.movierama.mappers;

import com.project.movierama.dtos.UserDto;
import com.project.movierama.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
