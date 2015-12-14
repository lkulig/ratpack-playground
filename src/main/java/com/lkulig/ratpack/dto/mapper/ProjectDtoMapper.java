package com.lkulig.ratpack.dto.mapper;

import static java.util.stream.Collectors.toSet;
import com.lkulig.ratpack.dto.ProjectDto;
import com.lkulig.ratpack.dto.ShortProjectDto;
import com.lkulig.ratpack.entity.Project;

public class ProjectDtoMapper {

    public static ProjectDto toProjectDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.id = project.getId();
        projectDto.name = project.getName();
        projectDto.users = project.getUsers().stream().map(UserDtoMapper::toShortUserDto).collect(toSet());
        return projectDto;
    }

    public static ShortProjectDto toShortProjectDto(Project project) {
        ShortProjectDto projectDto = new ShortProjectDto();
        projectDto.id = project.getId();
        projectDto.name = project.getName();
        return projectDto;
    }
}
