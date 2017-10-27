package com.aventstack.klov;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.aventstack.klov.configuration.WebConfiguration;
import com.aventstack.klov.controllers.ReportViewController;
import com.aventstack.klov.controllers.rest.ReportController;
import com.aventstack.klov.domain.KlovDocument;
import com.aventstack.klov.domain.User;
import com.aventstack.klov.repository.ReportRepository;
import com.aventstack.klov.repository.UserRepository;
import com.aventstack.klov.storage.StorageProperties;
import com.aventstack.klov.storage.StorageService;

@SpringBootApplication //(exclude = {SessionAutoConfiguration.class})
@ComponentScan(basePackageClasses = {
        ReportController.class,
        ReportViewController.class,
        KlovDocument.class, 
        ReportRepository.class,
        WebConfiguration.class,
        StorageService.class
})
@EnableConfigurationProperties(StorageProperties.class)
public class Application {
    
    @Autowired
    private UserRepository<User, String> userRepository;
    
    @Value("${server.admin.name}")
    private String adminName;
    @Value("${server.admin.key}")
    private String adminKey;
    
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    InitializingBean initDatabase() {
        return () -> {
            if (userRepository.findOneByName(adminName) == null) {
                userRepository.save(new User(adminName, adminKey, true));
            }
        };
    }
    
    /*
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
    */
}
