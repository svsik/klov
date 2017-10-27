package com.aventstack.klov.controllers.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.repository.ProjectRepository;

@RestController
@RequestMapping("/rest/projects")
public class ProjectController {
    
    @Autowired
    private ProjectRepository<Project, String> repo;
    
    @RequestMapping(value="/findActiveProject", method=RequestMethod.GET, produces="application/json")
    public Project findActiveProject(HttpSession session) {
        return (Project) session.getAttribute("project");
    }
    
    @RequestMapping(value="/assign", method=RequestMethod.GET, produces="application/json")
    public Boolean assign(HttpSession session, @RequestParam("name") String name) {
        List<Project> project = repo.findByName(name);
        if (project == null || project.size() == 0 || project.size() > 1) {
            reset(session);
            return false;
        }
        
        session.setAttribute("project", project.get(0));
        return true;
    }
    
    @RequestMapping(value="/reset", method=RequestMethod.POST, produces="application/json")
    @ResponseStatus(HttpStatus.OK)
    public void reset(HttpSession session) {
        Object p = session.getAttribute("project");
        String project = p == null ? "" : p.toString();
        
        if (!project.isEmpty()) {
            session.setAttribute("project", null);
        }
    }
    
}
