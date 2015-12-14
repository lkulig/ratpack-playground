package com.lkulig.ratpack.service.user;

import static java.util.stream.Collectors.toList;
import com.codahale.metrics.annotation.Timed;
import com.lkulig.ratpack.dto.UserDto;
import com.lkulig.ratpack.dto.mapper.UserDtoMapper;
import com.lkulig.ratpack.entity.Project;
import com.lkulig.ratpack.entity.User;
import com.lkulig.ratpack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Timed
    public List<UserDto> getUsers() {
        LOGGER.info("Finding all users");
        return userRepository.findAll().stream().map(UserDtoMapper::toUserDto).collect(toList());
    }

    @Timed
    public User createUser() {
        LOGGER.info("Creating new user");
        return userRepository.save(new User("4", "4"));
    }

    @Timed
    public User getById(long id) {
        LOGGER.info("Getting user by [id={}]", id);
        return userRepository.findOne(id);
    }

    @Timed
    public Void deleteUser(User user) {
        LOGGER.info("Deleting user");
        userRepository.delete(user);
        return null;
    }

    @Timed
    public User removeProject(User user, Project project) {
        LOGGER.info("Removing project from user");
        user.removeProject(project);
        return userRepository.save(user);
    }
}
