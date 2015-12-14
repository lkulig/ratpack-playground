package com.lkulig.ratpack.repository;

import org.springframework.data.repository.CrudRepository;
import com.lkulig.ratpack.entity.Project;
import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();
}
