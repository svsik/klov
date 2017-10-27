package com.aventstack.klov.repository.custom;

import java.util.List;

import com.aventstack.klov.domain.AggregationCount;

public interface AuthorRepositoryCustom {  
    
    public List<AggregationCount> findDistinct();
    
}
