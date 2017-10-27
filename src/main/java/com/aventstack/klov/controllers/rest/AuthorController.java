package com.aventstack.klov.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aventstack.klov.domain.AggregationCount;
import com.aventstack.klov.domain.Author;
import com.aventstack.klov.repository.AuthorRepository;

@RestController
@RequestMapping("/rest/authors")
public class AuthorController {

    @Autowired
    private AuthorRepository<Author, String> repo;
    
    @RequestMapping(value="/count", method=RequestMethod.GET, produces="application/json")
    public Long count() {
        return repo.count();
    }
    
    @RequestMapping(value="/findDistinct", method=RequestMethod.GET, produces="application/json")
    public List<AggregationCount> findDistinct() {
        return repo.findDistinct();
    }
    
}
