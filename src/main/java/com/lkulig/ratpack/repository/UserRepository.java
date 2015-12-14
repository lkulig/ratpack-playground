package com.lkulig.ratpack.repository;

import org.springframework.data.repository.CrudRepository;
import com.lkulig.ratpack.entity.User;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();
}
