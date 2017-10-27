package com.aventstack.klov.repository.custom;

import java.util.List;
import java.util.Optional;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Project;

public interface CategoryRepositoryCustom {  
    
    public List<AggregationCount> findDistinct(Optional<Project> project);
    
}
