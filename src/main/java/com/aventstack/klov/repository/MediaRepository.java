package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Media;

@RepositoryRestResource(collectionResourceRel = "media", path = "media")
public interface MediaRepository<T, ID extends Serializable> extends MongoRepository<Media, String> {

    long count();
    
    Media findById(@Param("id") String id);
        
    List<Media> findByTest(@Param("test") ObjectId test);
    
    List<Media> findByLog(@Param("log") ObjectId log);
    
    boolean exists(@Param("id") String id);
            
}
