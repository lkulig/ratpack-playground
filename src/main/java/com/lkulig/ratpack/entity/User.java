package com.lkulig.ratpack.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "USER_ID")
    private long id;
    private String firstName;
    private String lastName;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    @JoinTable(name = "USER_PROJECT", joinColumns = {
        @JoinColumn(name = "PROJECT_ID", nullable = false, updatable = false)},
        inverseJoinColumns = {@JoinColumn(name = "USER_ID",
            nullable = false, updatable = false)})
    @Fetch(FetchMode.SUBSELECT)
    private Set<Project> projects = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void addToProject(Project project) {
        projects.add(project);
    }

    public void removeProject(Project project) {
        projects.remove(project);
    }
}
