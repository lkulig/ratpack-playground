package com.lkulig.ratpack.api.project;

import com.lkulig.ratpack.api.ApiConstants;
import com.lkulig.ratpack.util.annotation.RatpackActionChain;
import org.springframework.beans.factory.annotation.Autowired;
import ratpack.func.Action;
import ratpack.handling.Chain;

@RatpackActionChain
public class ProjectsActionChain implements Action<Chain> {

    @Autowired
    private ProjectHandler projectHandler;

    @Autowired
    private ProjectsHandler projectsHandler;

    @Override
    public void execute(Chain chain) throws Exception {
        chain
            .prefix(ApiConstants.PROJECTS_API_PATH, usersChain -> {
                usersChain.prefix("/:id", userChain -> {
                    userChain.all(projectHandler);
                }).all(projectsHandler);
            });
    }
}
