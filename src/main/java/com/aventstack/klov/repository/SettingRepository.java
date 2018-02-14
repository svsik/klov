package com.aventstack.klov.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Setting;
import com.aventstack.klov.domain.User;

@RepositoryRestResource(exported = false)
public interface SettingRepository<T, ID extends Serializable> extends MongoRepository<Setting, String> {

    User findOneByName(@Param("name") String name);
    
}
