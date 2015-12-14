package com.lkulig.ratpack.entity;

import static javax.persistence.GenerationType.AUTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "PROJECT_ID")
    private long id;
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects")
    @Fetch(FetchMode.SELECT)
    private Set<User> users = new HashSet<>();

    public Project() {}

    public Project(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUsers() {
        return users;
    }
}
