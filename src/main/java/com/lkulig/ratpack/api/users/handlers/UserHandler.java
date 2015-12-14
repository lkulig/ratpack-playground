package com.lkulig.ratpack.api.users.handlers;

import static com.lkulig.ratpack.dto.mapper.UserDtoMapper.toUserDto;
import static ratpack.jackson.Jackson.json;
import org.springframework.beans.factory.annotation.Autowired;
import com.lkulig.ratpack.entity.User;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import com.lkulig.ratpack.service.user.AsyncUserService;
import com.lkulig.ratpack.service.user.UserService;
import com.lkulig.ratpack.util.annotation.RatpackHandler;

@RatpackHandler
public class UserHandler implements Handler {

    @Autowired
    private AsyncUserService asyncUserService;

    @Autowired
    private UserService userService;

    @Override
    public void handle(Context context) throws Exception {
        String id = context.getPathTokens().get("id");
        Promise<User> userPromise = asyncUserService.getById(Long.parseLong(id));
        context.byMethod(method -> method
                .delete(() -> userPromise.blockingOp(userService::deleteUser)
                    .then(success -> context.getResponse().status(204).send()))
                .get(() -> userPromise.then(user -> context.render(json(toUserDto(user)))))
        );
    }
}
