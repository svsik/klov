package com.aventstack.klov.controllers.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Category;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.repository.CategoryRepository;

@RestController
@RequestMapping("/rest/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository<Category, String> repo;
    
    @RequestMapping(value="/search/getCategoryTimeTakenAverageOverNReports", method=RequestMethod.GET, produces="application/json")
    public List<AggregationCount> getCategoryTimeTakenAverageOverNReports(@RequestParam("reportsLength") int reportsLength, HttpSession session) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        return repo.findTimeTakenForPastNReports(project, reportsLength);
    }
    
    @RequestMapping(value="/search/getCategoryTestLengthAverageOverNReports", method=RequestMethod.GET, produces="application/json")
    public List<AggregationCount> getCategoryTestLengthAverageOverNReports(@RequestParam("reportsLength") int reportsLength, HttpSession session) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        return repo.findTestLengthForPastNReports(project, reportsLength);
    }
    
    @RequestMapping(value="/search/findReportsByCategoryName", method=RequestMethod.GET, produces="application/json")
    public List<Report> findReportsByCategoryName(@RequestParam("name") String name, HttpSession session) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        return repo.findReportsByCategoryName(project, name);
    }
    
}
