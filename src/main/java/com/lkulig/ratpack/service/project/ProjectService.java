package com.lkulig.ratpack.service.project;

import static java.util.stream.Collectors.toList;
import static com.lkulig.ratpack.dto.mapper.ProjectDtoMapper.toProjectDto;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lkulig.ratpack.dto.ProjectDto;
import com.lkulig.ratpack.dto.mapper.ProjectDtoMapper;
import com.lkulig.ratpack.entity.Project;
import com.lkulig.ratpack.repository.ProjectRepository;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Timed
    public List<ProjectDto> getProjects() {
        LOGGER.info("Finding all projects");
        return projectRepository.findAll().stream().map(ProjectDtoMapper::toProjectDto).collect(toList());
    }

    @Timed
    public ProjectDto createProject() {
        LOGGER.info("Creating new project");
        Project createdProject = projectRepository.save(new Project("4"));
        return toProjectDto(createdProject);
    }

    @Timed
    public Project getById(long id) {
        LOGGER.info("Getting project by [id={}]", id);
        return projectRepository.findOne(id);
    }

    @Timed
    public Void deleteProject(Project project) {
        LOGGER.info("Deleting project");
        projectRepository.delete(project);
        return null;
    }
}
