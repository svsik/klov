package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.repository.custom.ProjectRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "project", path = "projects")
public interface ProjectRepository<T, ID extends Serializable> extends MongoRepository<Project, String>, ProjectRepositoryCustom {

    public long count();
    
    public Project findById(@Param("id") String id);
    
    public List<Project> findByName(@Param("name") String name);
    
    public boolean exists(@Param("id") String id);
    
    public List<Project> findAll();
        
}
