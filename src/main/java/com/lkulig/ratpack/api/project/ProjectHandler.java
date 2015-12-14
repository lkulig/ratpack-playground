package com.lkulig.ratpack.api.project;

import static ratpack.jackson.Jackson.json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.lkulig.ratpack.dto.ProjectDto;
import com.lkulig.ratpack.dto.mapper.ProjectDtoMapper;
import com.lkulig.ratpack.entity.Project;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import com.lkulig.ratpack.service.project.AsyncProjectService;
import com.lkulig.ratpack.service.project.ProjectService;
import com.lkulig.ratpack.util.annotation.RatpackHandler;

@RatpackHandler
public class ProjectHandler implements Handler {

    @Autowired
    private AsyncProjectService asyncProjectService;

    @Autowired
    private ProjectService projectService;

    @Transactional
    private ProjectDto getProject(Project project) {
        return ProjectDtoMapper.toProjectDto(project);
    }

    @Override
    public void handle(Context context) throws Exception {
        String id = context.getPathTokens().get("id");
        Promise<Project> projectPromise = asyncProjectService.getById(Long.parseLong(id));
        context.byMethod(method -> method
                .delete(() -> projectPromise.blockingOp(projectService::deleteProject)
                    .then(success -> context.getResponse().status(204).send()))
                .get(() -> projectPromise.then(project -> context.render(json(getProject(project)))))
        );
    }
}
