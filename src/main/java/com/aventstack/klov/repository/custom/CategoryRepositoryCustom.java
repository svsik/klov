package com.aventstack.klov.repository.custom;

import java.util.List;
import java.util.Optional;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;

@RepositoryRestResource(collectionResourceRel = "category", path = "categories")
public interface CategoryRepositoryCustom {  
    
    List<AggregationCount> findDistinct(Optional<Project> project);
    
    List<AggregationCount> findTimeTakenForPastNReports(Optional<Project> project, int reportsLength);
    
    List<AggregationCount> findTestLengthForPastNReports(Optional<Project> project, int reportsLength);

    List<Report> findReportsByCategoryName(Optional<Project> project, String name);
}
