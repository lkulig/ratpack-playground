package com.lkulig.ratpack.dto.mapper;

import static java.util.stream.Collectors.toSet;
import com.lkulig.ratpack.dto.ShortUserDto;
import com.lkulig.ratpack.dto.UserDto;
import com.lkulig.ratpack.entity.User;

public class UserDtoMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.firstName = user.getFirstName();
        userDto.surname = user.getLastName();
        userDto.id = user.getId();
        userDto.projects = user.getProjects().stream().map(ProjectDtoMapper::toShortProjectDto).collect(toSet());
        return userDto;
    }

    public static ShortUserDto toShortUserDto(User user) {
        ShortUserDto userDto = new ShortUserDto();
        userDto.firstName = user.getFirstName();
        userDto.surname = user.getLastName();
        userDto.id = user.getId();
        return userDto;
    }
}
