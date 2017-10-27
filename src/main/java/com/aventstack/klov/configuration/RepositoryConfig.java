package com.aventstack.klov.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.aventstack.klov.domain.Log;
import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.domain.Test;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Project.class, Report.class, Test.class, Log.class);
    }
    
}