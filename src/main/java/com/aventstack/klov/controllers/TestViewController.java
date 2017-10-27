package com.aventstack.klov.controllers;

import java.util.List;
import java.util.Map;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aventstack.klov.domain.Test;
import com.aventstack.klov.repository.TestRepository;
import com.aventstack.klov.viewdefs.Color;

@Controller
@Scope("session")
public class TestViewController {
    
    @Autowired
    private TestRepository<Test, String> testRepo;

    @GetMapping("/test")
    public String test(@RequestParam("id") String id, Map<String, Object> model) {
        Test test = testRepo.findById(id);
        model.put("test", test);
        
        List<Test> testHistory = testRepo.findHistory(id);
        testHistory.add(0, test);
        model.put("testHistory", testHistory);
        
        model.put("isBDD", test.getBdd());
        model.put("Color", new Color());
        model.put("PrettyTime", new PrettyTime());
        
        return "test";
    }
    
}
