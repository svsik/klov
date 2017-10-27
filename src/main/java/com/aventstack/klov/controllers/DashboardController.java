package com.aventstack.klov.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Author;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.domain.Test;
import com.aventstack.klov.domain.WeeklyReportAggregation;
import com.aventstack.klov.repository.AuthorRepository;
import com.aventstack.klov.repository.CategoryRepository;
import com.aventstack.klov.repository.ProjectRepository;
import com.aventstack.klov.repository.ReportRepository;
import com.aventstack.klov.repository.TestRepository;
import com.aventstack.klov.viewdefs.Color;

@Controller
@Scope("session")
public class DashboardController {

    @Autowired
    private ProjectRepository<Project, String> projectRepo;
    
    @Autowired
    private TestRepository<Test, String> testRepo;
    
    @Autowired
    private ReportRepository<Report, String> reportRepo;
    
    @Autowired
    private AuthorRepository<Author, String> authorRepo;
    
    @Autowired
    private CategoryRepository<Category, String> categoryRepo;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Pageable pageable, Map<String, Object> model) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        model.put("project", project);
        
        Long millis = DateTime.now().withTimeAtStartOfDay().getMillis();
        Date startOfDay = new Date(millis);
        
        List<Project> projectList = projectRepo.findAll();
        model.put("projectList", projectList);
        
        Long reportTodayLength = reportRepo.countByProjectAndStartTimeGreaterThan(project, startOfDay);
        model.put("reportTodayLength", reportTodayLength);
        
        Long testTodayLength = testRepo.countByProjectAndStartTimeGreaterThan(project, startOfDay);
        model.put("testTodayLength", testTodayLength);
        
        Long reportLength = reportRepo.countByProject(project);
        model.put("reportLength", reportLength);
        
        Long testLength = testRepo.countByProject(project);
        model.put("testLength", testLength);
        
        List<AggregationCount> topFailedList = testRepo.findByNameByProjectByTopFailed(project);
        model.put("topFailedList", topFailedList);
        
        List<Test> testList = testRepo.findFirstNByProjectOrderByEndTimeDesc(15, project);
        model.put("testList", testList);
        
        List<WeeklyReportAggregation> weeklyList = reportRepo.findByPeriodicAggregation(project, 7);
        model.put("weeklyList", weeklyList);
        
        List<AggregationCount> authorList = authorRepo.findDistinct();
        model.put("authorList", authorList);

        List<AggregationCount> categoryList = categoryRepo.findDistinct(project);
        model.put("categoryList", categoryList);
        
        model.put("projectList", projectRepo.findAll());
        model.put("Color", new Color());
        model.put("prettyTime", new PrettyTime());
        
        return "dashboard";
    }
    
}
