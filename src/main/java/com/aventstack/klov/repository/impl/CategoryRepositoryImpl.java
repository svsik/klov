package com.aventstack.klov.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.repository.ReportRepository;
import com.aventstack.klov.repository.custom.CategoryRepositoryCustom;

@Repository
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ReportRepository<Report, String> reportRepo;
    
    @Override
    public List<AggregationCount> findDistinct(Optional<Project> project) {
        MatchOperation match = Aggregation.match(Criteria.where("name").ne(null));
        if (project.isPresent())
            match = Aggregation.match(Criteria.where("name").ne(null).and("project").is(new ObjectId(project.get().getId())));
        
        GroupOperation group = Aggregation.group("name").count().as("total");
        ProjectionOperation projection = Aggregation.project("total").and("name").previousOperation();
        Aggregation pipeline = newAggregation(match, group, projection);
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Category.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }
    
    @Override
    public List<AggregationCount> findTimeTakenForPastNReports(Optional<Project> project, int reportLength) {
        List<Report> reportList = reportRepo.findIdByCountOrderByDateTimeDesc(project, reportLength);
        List<ObjectId> ids = reportList.stream().map(Report::getId).map(x -> new ObjectId(x)).collect(Collectors.toList()); 
        
        Criteria c = Criteria
                .where("name").ne(null)
                .and("timeTaken").ne(null)
                .and("report").in(ids);
        MatchOperation match = Aggregation.match(c);
        GroupOperation group = Aggregation.group("name").avg("timeTaken").as("total");
        ProjectionOperation projection = Aggregation.project("total").and("name").previousOperation();
        Aggregation pipeline = newAggregation(match, group, projection);
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Category.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }

    @Override
    public List<AggregationCount> findTestLengthForPastNReports(Optional<Project> project, int reportLength) {
        List<Report> reportList = reportRepo.findIdByCountOrderByDateTimeDesc(project, reportLength);
        List<ObjectId> ids = reportList.stream().map(Report::getId).map(x -> new ObjectId(x)).collect(Collectors.toList()); 
        
        Criteria c = Criteria
                .where("name").ne(null)
                .and("testLength").ne(null)
                .and("report").in(ids);
        MatchOperation match = Aggregation.match(c);
        GroupOperation group = Aggregation.group("name").avg("testLength").as("total");
        ProjectionOperation projection = Aggregation.project("total").and("name").previousOperation();
        Aggregation pipeline = newAggregation(match, group, projection);
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Category.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }
    
    @Override
    public List<Report> findReportsByCategoryName(Optional<Project> project, String name) {
        Query query = new Query(Criteria.where("categoryNameList").in(name).and("project").is(new ObjectId(project.get().getId())))
                .with(new Sort(Sort.Direction.DESC, "startTime"));
        List<Report> list = mongoTemplate.find(query, Report.class);
        return list;
    }
    
}
