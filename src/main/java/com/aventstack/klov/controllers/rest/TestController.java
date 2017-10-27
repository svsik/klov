package com.aventstack.klov.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aventstack.klov.domain.Test;
import com.aventstack.klov.repository.TestRepository;

@RestController
@RequestMapping("/rest/tests")
public class TestController {

    @Autowired
    private TestRepository<Test, String> repo;
    
    @RequestMapping(value="/count", method=RequestMethod.GET, produces="application/json")
    public Long count() {
        return repo.count();
    }
    
    @RequestMapping(value="/findHistory", method=RequestMethod.GET, produces="application/json")
    public List<Test> findHistory(@RequestParam("id") String id) {
        return repo.findHistory(id);
    }
    
    @RequestMapping(value="/findNodes", method=RequestMethod.GET, produces="application/json")
    public List<Test> findNodes(@RequestParam("id") String id) {
        return repo.findNodes(id);
    }
    
    @RequestMapping(value="/deepPopulate", method=RequestMethod.GET, produces="application/json")
    public Test deepPopulate(@RequestParam("id") String id) {
        return repo.deepPopulate(id);
    }    
    
    @RequestMapping(value="/countLeafTests", method=RequestMethod.GET, produces="application/json")
    public Long countLeafTests() {
        return repo.countByChildNodesLength(0);
    }
    
}
