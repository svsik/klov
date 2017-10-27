package com.aventstack.klov.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.repository.custom.ReportRepositoryCustom;

@RepositoryRestResource(collectionResourceRel = "report", path = "reports")
public interface ReportRepository<T, ID extends Serializable> extends MongoRepository<Report, String>, ReportRepositoryCustom {

    long count();

    Long countByProject(Optional<Project> project);
    
    Report findById(@Param("id") String id);
    
    List<Report> findByName(@Param("name") String name);
    
    List<Report> findAll();
    
    List<Report> findByProject(@Param("project") ObjectId project);

    List<Report> findByStartTimeGreaterThan(@Param("date") Date date);
    
    List<Report> findByCategoryNameList(@Param("name") String name);

}
