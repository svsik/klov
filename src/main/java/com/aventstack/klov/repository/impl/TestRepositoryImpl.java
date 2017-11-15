package com.aventstack.klov.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.IncludesMedia;
import com.aventstack.klov.domain.Log;
import com.aventstack.klov.domain.Media;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Test;
import com.aventstack.klov.repository.MediaRepository;
import com.aventstack.klov.repository.custom.TestRepositoryCustom;
import com.aventstack.klov.storage.StorageService;

@Repository
public class TestRepositoryImpl implements TestRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Autowired
    private MediaRepository<Media, String> mediaRepo;

    private final StorageService storageService;

    @Autowired
    public TestRepositoryImpl(StorageService storageService) {
        this.storageService = storageService;
    }
    
    @Override
    public List<Test> findHistory(String id) {
        Test test = findOne(id);
        Query query = new Query(Criteria.where("name").is(test.getName()).and("_id").ne(id))
                .with(new Sort(Sort.Direction.DESC, "startTime"));
        List<Test> list = mongoTemplate.find(query, Test.class);
        return list;
    }

    @Override
    public List<Test> findNodes(String id) {
        Query query = new Query(Criteria.where("parent").is(new ObjectId(id)));
        List<Test> list = mongoTemplate.find(query, Test.class);
        return list;
    }
    
    @Override
    public Test deepPopulate(String id) {
        Test test = findOne(id);
        test.setLogs(findLogs(id));
        assignMedia(test);
        
        if (test.getChildNodesLength() > 0) {
            List<Test> nodes = findNodes(id);
            
            nodes.forEach(node -> {
                node.setLogs(findLogs(node.getId()));
                node.setNodes(deepPopulate(node.getId()).getNodes());
                assignMedia(node);
            });
            test.setNodes(nodes);
        }
        
        return test;
    }
    
    private void assignMedia(Test test) {
        List<Media> media = mediaRepo.findByTest(new ObjectId(test.getId()));
        assignMedia(test, media);
    }
    
    @Override
    public List<Log> findLogs(String id) {
        Query query = new Query(Criteria.where("test").is(new ObjectId(id)));
        List<Log> list = mongoTemplate.find(query, Log.class);
        
        list.forEach(x -> {
            assignMedia(x);
        });
        
        return list;
    }
    
    private void assignMedia(Log log) {
        List<Media> media = mediaRepo.findByLog(new ObjectId(log.getId()));
        assignMedia(log, media);
    }
    
    private void assignMedia(IncludesMedia el, List<Media> media) {
        media.forEach(m -> {
            String base64String = storageService.loadBase64(m);
            if (base64String != null && !base64String.isEmpty()) {
                m.setBase64String(storageService.loadBase64(m));
                el.setMedia(media);
            }
        });
    }
    
    private Test findOne(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        Test test = mongoTemplate.findOne(query, Test.class);
        return test;
    }

    @Override
    public List<Test> findFirstNByProjectOrderByEndTimeDesc(Integer n, Optional<Project> project) {
        Query query = new Query().with(new Sort(Sort.Direction.DESC, "endTime")).limit(n);
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.find(query, Test.class);
    }
    
    @Override
    public List<AggregationCount> findByNameByProjectByTopFailed(Optional<Project> project) {
        Criteria c = Criteria.where("name").ne(null).and("status").is("fail");
        if (project.isPresent())
            c.and("project").is(new ObjectId(project.get().getId()));
        
        Aggregation pipeline = newAggregation(
                match(c),
                group("name").count().as("total"),
                project("total").and("name").previousOperation()
        );
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Test.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }
    
    @Override
    public List<AggregationCount> findFailedTestLengthByProjectCategories(Optional<Project> project) {
        Criteria c = Criteria
                .where("name").ne(null)
                .and("categoryNameList").ne(null)
                .and("status").is("fail")
                .and("project").is(new ObjectId(project.get().getId()));

        Aggregation pipeline = newAggregation(
                match(c),
                group("name").count().as("total"),
                project("total").and("name").previousOperation()
        );
        AggregationResults<AggregationCount> groupResults = mongoTemplate.aggregate(pipeline, Test.class, AggregationCount.class);
        
        return groupResults.getMappedResults();
    }

    @Override
    public Long countByProjectAndStartTimeGreaterThan(Optional<Project> project, Date date) {
        Query query = new Query(Criteria.where("startTime").gt(date).and("leaf").is(true));
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.count(query, Test.class);
    }
    
    @Override
    public Long countByProject(Optional<Project> project) {
        Query query = new Query(Criteria.where("leaf").is(true));
        if (project.isPresent())
            query.addCriteria(Criteria.where("project").is(new ObjectId(project.get().getId())));
        
        return mongoTemplate.count(query, Test.class);
    }

}
