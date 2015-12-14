package com.lkulig.ratpack.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lkulig.ratpack.dto.UserDto;
import com.lkulig.ratpack.entity.Project;
import com.lkulig.ratpack.entity.User;
import ratpack.exec.Blocking;
import ratpack.exec.Promise;
import java.util.List;

@Service
public class AsyncUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncUserService.class);

    @Autowired
    private UserService userService;

    public Promise<List<UserDto>> getAll() {
        LOGGER.info("Getting all users");
        return Blocking.get(userService::getUsers);
    }

    public Promise<User> getById(long id) {
        LOGGER.info("Getting user by [id={}]", id);
        return Blocking.get(() -> userService.getById(id));
    }

    public Promise<User> create() {
        LOGGER.info("Creating user entity");
        return Blocking.get(userService::createUser);
    }

    public Promise<User> removeProject(User user, Project project) {
        LOGGER.info("Removing project from user");
        return Blocking.get(() -> userService.removeProject(user, project));
    }
}