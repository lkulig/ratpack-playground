package com.lkulig.ratpack.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lkulig.ratpack.entity.Project;
import com.lkulig.ratpack.entity.User;
import com.lkulig.ratpack.repository.ProjectRepository;
import com.lkulig.ratpack.repository.UserRepository;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInjector {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initData() {
        Project project = new Project("myProject");
        List<User> users = new ArrayList<>();
        User user = new User("1", "1");
        user.addToProject(project);
        users.add(user);
        users.add(new User("2", "2"));
        users.add(new User("3", "3"));
        userRepository.save(users);
    }

}
