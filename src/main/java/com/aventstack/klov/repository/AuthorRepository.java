package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Author;
import com.aventstack.klov.repository.custom.AuthorRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "author", path = "authors")
public interface AuthorRepository<T, ID extends Serializable> extends MongoRepository<Author, String>, AuthorRepositoryCustom {

    long count();
    
    Author findById(@Param("id") String id);
    
    List<Author> findByName(@Param("name") String name);
    
    boolean exists(@Param("id") String id);
    
    List<Author> findAll();
            
}
