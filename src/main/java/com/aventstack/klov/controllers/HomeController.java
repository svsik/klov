package com.aventstack.klov.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.repository.ProjectRepository;

@Controller
public class HomeController {

    @Autowired
    private ProjectRepository<Project, String> projectRepo;
    
    @GetMapping("/")
    public String home(HttpSession session, Map<String, Object> model) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        
        if (project.isPresent()) {
            return "redirect:/builds";
        } else {
            List<Project> projectList = projectRepo.findAll();
            model.put("projectList", projectList);
            
            return "redirect:/projects";
        }   
    }
    
    @RequestMapping(value="/assignProjectById", method=RequestMethod.POST, produces="application/json")
    public String assignById(HttpSession session, @RequestParam("id") String id) {
        Project project = projectRepo.findById(id);

        if (project == null)
            return "redirect:/projects";

        session.setAttribute("project", project);
        return "redirect:/builds";
    }
    
}
