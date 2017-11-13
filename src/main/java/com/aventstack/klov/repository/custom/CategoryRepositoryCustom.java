package com.aventstack.klov.repository.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Project;

@RepositoryRestResource(collectionResourceRel = "category", path = "categories")
public interface CategoryRepositoryCustom {  
    
    public List<AggregationCount> findDistinct(Optional<Project> project);
    
    public List<AggregationCount> findTimeTakenForPastNReports(Optional<Project> project, int reportsLength);
    
    public List<AggregationCount> findTestLengthForPastNReports(Optional<Project> project, int reportsLength);
}
