package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Category;
import com.aventstack.klov.repository.custom.CategoryRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "category", path = "categories")
public interface CategoryRepository<T, ID extends Serializable> extends MongoRepository<Category, String>, CategoryRepositoryCustom {

    long count();
    
    Category findById(@Param("id") String id);
    
    List<Category> findByName(@Param("name") String name);
    
    boolean exists(@Param("id") String id);
    
    List<Category> findAll();
            
}
