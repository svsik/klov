package com.aventstack.klov.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.repository.CategoryRepository;
import com.aventstack.klov.repository.ProjectRepository;

@Controller
@Scope("session")
public class CategoryViewController {

    @Autowired
    private CategoryRepository<Category, String> categoryRepo;
    
    @Autowired
    private ProjectRepository<Project, String> projectRepo;
    
    @GetMapping("/tags")
    public String tagSummary(HttpSession session, Map<String, Object> model) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        model.put("project", project);
        
        List<AggregationCount> categoryList = categoryRepo.findDistinct(project);
        model.put("categoryList", categoryList);
        
        model.put("projectList", projectRepo.findAll());
        
        return "tags";
    }

}
