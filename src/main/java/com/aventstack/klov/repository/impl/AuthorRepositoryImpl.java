package com.aventstack.klov.repository.impl;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Author;
import com.aventstack.klov.repository.custom.AuthorRepositoryCustom;

@Repository
public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public List<AggregationCount> findDistinct() {
        Aggregation pipeline = newAggregation(
                match(Criteria.where("name").ne(null)),
                group("name").count().as("total"),
                project("total").and("name").previousOperation()
        );
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Author.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }
    
}
