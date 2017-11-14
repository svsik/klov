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

    public long count();
    
    public Long countByReport(@Param("report") ObjectId report);
    
    public Long countByReportAndLevel(@Param("report") ObjectId report, @Param("level") Integer level);
    
    public Long countByProject(@Param("project") ObjectId project);
    
    public Long countByChildNodesLength(@Param("length") Integer length);
    
    public Test findById(@Param("id") String id);
    
    public List<Test> findByName(@Param("name") String name);
    
    public boolean exists(@Param("id") String id);
    
    public List<Test> findAll();
    
    public List<Test> findFirst10ByOrderByEndTimeDesc();
    
    public List<Test> findFirst10ByProjectOrderByEndTimeDesc(@Param("project") ObjectId project);
    
    public List<Test> findByReport(@Param("report") ObjectId report);
    
    public List<Test> findByReportAndLevel(@Param("report") ObjectId report, @Param("level") Integer level); //
    
    public List<Test> findByReportAndLevelAndStatus(@Param("report") ObjectId report, @Param("level") Integer level, @Param("status") String status); //
    
    public List<Test> findByProject(@Param("project") ObjectId project);
    
    public List<Test> findByStartTimeGreaterThan(@Param("date") Date date);
    
    public List<Test> findByReportAndCategoryNameListIn(@Param("report") ObjectId report, @Param("categoryName") String categoryName);
    
}
