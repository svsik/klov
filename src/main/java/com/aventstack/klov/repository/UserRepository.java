package com.aventstack.klov.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.aventstack.klov.domain.User;

@RepositoryRestResource(exported = false)
public interface UserRepository<T, ID extends Serializable> extends MongoRepository<User, String> {

    @RestResource(exported = false)
    User findOneByName(@Param("name") String name);
    
}
