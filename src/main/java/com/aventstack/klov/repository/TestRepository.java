package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Test;
import com.aventstack.klov.repository.custom.TestRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "test", path = "tests")
public interface TestRepository<T, ID extends Serializable> extends MongoRepository<Test, String>, TestRepositoryCustom {

    long count();
    
    Long countByReport(@Param("report") ObjectId report);
    
    Long countByReportAndLevel(@Param("report") ObjectId report, @Param("level") Integer level);
    
    Long countByProject(@Param("project") ObjectId project);
    
    Long countByChildNodesLength(@Param("length") Integer length);
    
    Test findById(@Param("id") String id);
    
    List<Test> findByName(@Param("name") String name);
    
    boolean exists(@Param("id") String id);
    
    List<Test> findAll();
    
    List<Test> findFirst10ByOrderByEndTimeDesc();
    
    List<Test> findFirst10ByProjectOrderByEndTimeDesc(@Param("project") ObjectId project);
    
    List<Test> findByReport(@Param("report") ObjectId report);
    
    List<Test> findByReportAndLevel(@Param("report") ObjectId report, @Param("level") Integer level); //
    
    List<Test> findByReportAndLevelAndStatus(@Param("report") ObjectId report, @Param("level") Integer level, @Param("status") String status); //
    
    List<Test> findByProject(@Param("project") ObjectId project);
    
    List<Test> findByStartTimeGreaterThan(@Param("date") Date date);
    
    List<Test> findByReportAndCategoryNameListIn(@Param("report") ObjectId report, @Param("categoryName") String categoryName);
    
}
