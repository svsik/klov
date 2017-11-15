package com.aventstack.klov.repository.custom;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Log;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Test;

public interface TestRepositoryCustom {
    
    List<Test> findHistory(String id);
    
    List<Test> findNodes(String id);
    
    List<Log> findLogs(String id);
    
    Test deepPopulate(String id);
    
    Long countByProjectAndStartTimeGreaterThan(Optional<Project> project, Date date);

    Long countByProject(Optional<Project> project);

    List<AggregationCount> findByNameByProjectByTopFailed(Optional<Project> project);
    
    List<Test> findFirstNByProjectOrderByEndTimeDesc(Integer n, Optional<Project> project);

    List<AggregationCount> findFailedTestLengthByProjectCategories(Optional<Project> project);
    
}
