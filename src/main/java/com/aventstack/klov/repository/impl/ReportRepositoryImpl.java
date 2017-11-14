package com.aventstack.klov.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.Assert;

import com.aventstack.klov.domain.Author;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Environment;
import com.aventstack.klov.domain.Log;
import com.aventstack.klov.domain.Media;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.domain.Test;
import com.aventstack.klov.domain.WeeklyReportAggregation;
import com.aventstack.klov.repository.custom.ReportRepositoryCustom;

public class ReportRepositoryImpl implements ReportRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Report> findByCountOrderByDateTimeDesc(int count) {
        return mongoTemplate.find(new Query().limit(count), Report.class);
    }
    
    @Override
    public List<Report> findIdByCountOrderByDateTimeDesc(Optional<Project> project, int count) {
        Query q = new Query().limit(count).with(new Sort(Sort.Direction.DESC, "startTime"));
        q.fields().include("id");
        
        if (project.isPresent())
            q.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.find(q, Report.class);
    }

    @Override
    public Long countByProjectAndStartTimeGreaterThan(Optional<Project> project, Date date) {
        Query query = new Query(Criteria.where("startTime").gt(date));
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.count(query, Report.class);
    }
    
    @Override
    public Long countByProject(Optional<Project> project) {
        Query query = new Query();
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.count(query, Report.class);
    }
    
    @Override
    public Page<Report> findByProjectOrderByStartTimeDesc(Optional<Project> project, Pageable pageable) {
        Query query = new Query();
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        query.with(new Sort(Sort.Direction.DESC, "startTime")).with(pageable);
        List<Report> list = mongoTemplate.find(query, Report.class);

        PageImpl<Report> paged = new PageImpl<Report>(list, pageable, list.size());
        return paged;
    }
    
    @Override
    public List<WeeklyReportAggregation> findByPeriodicAggregation(Optional<Project> project, int daysPast) {
        List<Date> startDates = getDates(daysPast, TimeOfDay.BEGINNING_OF_DAY);
        List<Date> endDates = getDates(daysPast, TimeOfDay.END_OF_DAY);
        
        Assert.assertEquals(startDates.size(), endDates.size(), 
                "Start and End dates must have the same number of elements");
        
        Aggregation pipeline;
        AggregationResults<WeeklyReportAggregation> groupResults;
        List<WeeklyReportAggregation> list = new ArrayList<WeeklyReportAggregation>();

        AggregationOperation aggregationGroup = Aggregation.group()
                .sum("parentLength").as("total")
                .sum("passParentLength").as("passTotal")
                .sum("skipParentLength").as("skipTotal");
        AggregationOperation aggregationProject = Aggregation.project("total")
                .and("passTotal").as("passTotal")
                .and("skipTotal").as("skipTotal");
        
        for (int ix = 0; ix < startDates.size(); ix++) {
            if (project.isPresent()) {
                pipeline = newAggregation(
                        match(Criteria.where("project").is(new ObjectId(project.get().getId())).and("startTime").gt(startDates.get(ix)).and("endTime").lt(endDates.get(ix))),
                        aggregationGroup,
                        aggregationProject
                );
            } else {
                pipeline = newAggregation(
                        match(Criteria.where("startTime").gt(startDates.get(ix)).and("endTime").lt(endDates.get(ix))),
                        aggregationGroup,
                        aggregationProject
                );
            }
            
            groupResults = mongoTemplate.aggregate(pipeline, Report.class, WeeklyReportAggregation.class);
            
            if (groupResults.getMappedResults().size() > 0) {
                list.addAll(groupResults.getMappedResults());
                list.get(list.size()-1).setStartTime(startDates.get(ix));
            }
        }
        
        return list;
    }
    
    private enum TimeOfDay {
        BEGINNING_OF_DAY,
        END_OF_DAY
    }
    
    private List<Date> getDates(int daysPast, TimeOfDay timeOfDay) {
        DateTime today = DateTime.now();
        List<Date> dates = new ArrayList<Date>();
        
        if (TimeOfDay.BEGINNING_OF_DAY == timeOfDay) {
            dates.add(today.withTimeAtStartOfDay().toDate());
            for (int ix = 1; ix < daysPast; ix++) {
                dates.add(today.minus(Period.days(ix)).withTimeAtStartOfDay().toDate());
            }
        }
        
        if (TimeOfDay.END_OF_DAY == timeOfDay) {
            dates.add(today.withTime(23,59,59,999).toDate());
            for (int ix = 1; ix < daysPast; ix++) {
                dates.add(today.minus(Period.days(ix)).withTime(23,59,59,999).toDate());
            }
        }
        
        Assert.assertTrue(dates.size()>0, "List<Date> dates must contain atleast 1 element");
        
        return dates;
    }
    
    @Override
    public List<Report> findByCategoryNameList(Optional<Project> project, String name) {
        Query query = new Query(Criteria.where("categoryNameList").in(name));
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        query.with(new Sort(Sort.Direction.DESC, "startTime"));
        List<Report> list = mongoTemplate.find(query, Report.class);
        return list;
    }

    @Override
    public void remove(String id) {
        Report report = findOne(id);
        String projectId = report.getProject();
        
        Query query = new Query(Criteria.where("report").is(new ObjectId(id)));
        
        mongoTemplate.remove(query, Author.class);
        mongoTemplate.remove(query, Category.class);
        mongoTemplate.remove(query, Environment.class);
        mongoTemplate.remove(query, com.aventstack.klov.domain.Exception.class);
        mongoTemplate.remove(query, Log.class);
        mongoTemplate.remove(query, Media.class);
        mongoTemplate.remove(query, Test.class);
        mongoTemplate.remove(report);
        
        // if the project now has no reports, the project will be removed
        query = new Query(Criteria.where("project").is(new ObjectId(projectId)));
        Long reportLength = mongoTemplate.count(query, Report.class);
        if (reportLength == 0) {
            query = new Query(Criteria.where("id").is(projectId));
            mongoTemplate.remove(query, Project.class);
        }
    }
    
    private Report findOne(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Report report = mongoTemplate.findOne(query, Report.class);
        return report;
    }
   
}
