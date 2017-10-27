package com.aventstack.klov.repository.impl;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.repository.custom.CategoryRepositoryCustom;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AggregationCount> findDistinct(Optional<Project> project) {
        MatchOperation match = Aggregation.match(Criteria.where("name").ne(null));
        GroupOperation group = Aggregation.group("name").count().as("total");
        ProjectionOperation projection = Aggregation.project("total").and("name").previousOperation();
        
        if (project.isPresent())
            match = Aggregation.match(Criteria.where("name").ne(null).and("project").is(new ObjectId(project.get().getId())));
        
        Aggregation pipeline = newAggregation(match, group, projection);
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Category.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }
    
}
