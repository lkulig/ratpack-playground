package com.lkulig.ratpack.api.users.handlers;

import static ratpack.jackson.Jackson.json;
import org.springframework.beans.factory.annotation.Autowired;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import com.lkulig.ratpack.service.user.AsyncUserService;
import com.lkulig.ratpack.util.annotation.RatpackHandler;

@RatpackHandler
public class UsersHandler implements Handler{

    @Autowired
    private AsyncUserService asyncUserService;

    @Override
    public void handle(Context usersContext) throws Exception {
        usersContext.byMethod(method -> method
            .get(() -> asyncUserService.getAll().then(users -> usersContext.render(json(users))))
            .post(() -> asyncUserService.create().then(user -> usersContext.render(json(user)))));
    }
}
