package com.lkulig.ratpack.api.project;

import static ratpack.jackson.Jackson.json;
import org.springframework.beans.factory.annotation.Autowired;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import com.lkulig.ratpack.service.project.AsyncProjectService;
import com.lkulig.ratpack.util.annotation.RatpackHandler;

@RatpackHandler
public class ProjectsHandler implements Handler{

    @Autowired
    private AsyncProjectService asyncProjectService;

    @Override
    public void handle(Context context) throws Exception {
        context.byMethod(method -> method
            .get(() -> asyncProjectService.getAll().then(projects -> context.render(json(projects))))
            .post(() -> asyncProjectService.create().then(project -> context.render(json(project)))));
    }
}
