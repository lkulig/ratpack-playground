package com.lkulig.ratpack.api.users;

import static com.lkulig.ratpack.api.ApiConstants.USERS_API_PATH;
import static ratpack.handling.RequestLogger.ncsa;
import com.lkulig.ratpack.api.users.handlers.UserHandler;
import com.lkulig.ratpack.api.users.handlers.UsersHandler;
import com.lkulig.ratpack.util.annotation.RatpackActionChain;
import org.springframework.beans.factory.annotation.Autowired;
import ratpack.func.Action;
import ratpack.handling.Chain;

@RatpackActionChain
public class UsersActionChain implements Action<Chain> {

    @Autowired
    private UsersHandler usersHandler;

    @Autowired
    private UserHandler userHandler;

    @Override
    public void execute(Chain chain) throws Exception {
        chain
            .all(ncsa())
            .prefix(USERS_API_PATH, usersChain -> {
                usersChain
                    .prefix("/:id", userChain -> {
                        userChain.all(userHandler);
                    })
                    .all(usersHandler);
            });
    }
}
