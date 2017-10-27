package com.aventstack.klov.controllers.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.domain.WeeklyReportAggregation;
import com.aventstack.klov.repository.ReportRepository;

@RestController
@RequestMapping("/rest/reports")
public class ReportController implements ResourceProcessor<RepositorySearchesResource>, ResourceAssembler<Report, Resource<Report>> {

    @Autowired
    private ReportRepository<Report, String> repo;
    
    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(value="/search/findByProjectOrderByStartTimeDesc", method=RequestMethod.GET, produces="application/json")
    public Page<Report> findByProjectOrderByStartTimeDesc(HttpSession session, Pageable pageable) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        Page<Report> list = (Page<Report>) repo.findByProjectOrderByStartTimeDesc(project, pageable);
        return list;
    }
    
    @RequestMapping(value="/search/findByCountOrderByDateTimeDesc", method=RequestMethod.GET, produces="application/json")
    public List<Report> findByCountOrderByDateTimeDesc(@RequestParam("count") int count) {
        List<Report> list = repo.findByCountOrderByDateTimeDesc(count);
        return list;
    }
        
    @RequestMapping(value="/remove/{id}", method = RequestMethod.DELETE)
    public Boolean remove(@PathVariable("id") String id) {
        if (repo.exists(id)) {
            repo.remove(id);
            return true;
        }
        
        System.out.println("no report matching id " + id + " was found");        
        return false;
    }
    
    @RequestMapping(value="/search/findByCategoryNameList", method=RequestMethod.GET, produces="application/json")
    public List<Report> findByCategoryNameList(@RequestParam("name") String name, HttpSession session) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        List<Report> list = repo.findByCategoryNameList(project, name);
        return list;
    }
    
    @RequestMapping(value="/search/getPeriodReportAggregation", method=RequestMethod.GET, produces="application/json")
    public List<WeeklyReportAggregation> getPeriodReportAggregation(@RequestParam("daysPast") int daysPast, HttpSession session) {
        Optional<Project> project = Optional.ofNullable((Project) session.getAttribute("project"));
        return repo.findByPeriodicAggregation(project, daysPast);
    }
    
    @Override
    public RepositorySearchesResource process(RepositorySearchesResource resource) {
        LinkBuilder lb = entityLinks.linkFor(Report.class);
        resource.add(new Link(lb.toString() + "/search/findByOrderByStartTimeDesc(?page)", "findByOrderByStartTimeDesc"));
        resource.add(new Link(lb.toString() + "/search/findByCountOrderByDateTimeDesc(?count)", "findByCountOrderByDateTimeDesc"));
        return resource;
    }

    @Override
    public Resource<Report> toResource(Report report) {
        Resource<Report> resource = new Resource<Report>(report);
        return resource;
    }

}
