package com.lkulig.ratpack.service.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lkulig.ratpack.dto.ProjectDto;
import com.lkulig.ratpack.entity.Project;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import java.util.List;

@Service
public class AsyncProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProjectService.class);

    @Autowired
    private ProjectService projectService;

    public Promise<List<ProjectDto>> getAll() {
        LOGGER.info("Getting all users");
        return Blocking.get(projectService::getProjects);
    }

    public Promise<Project> getById(long id) {
        LOGGER.info("Getting user by [id={}]", id);
        return Blocking.get(() -> projectService.getById(id));
    }

    public Promise<ProjectDto> create() {
        LOGGER.info("Creating simple entity");
        return Blocking.get(projectService::createProject);
    }
}
