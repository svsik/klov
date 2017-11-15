package com.aventstack.klov.repository.custom;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aventstack.klov.domain.Project;
import com.aventstack.klov.domain.Report;
import com.aventstack.klov.domain.WeeklyReportAggregation;

public interface ReportRepositoryCustom {  

    Long countByProjectAndStartTimeGreaterThan(Optional<Project> project, Date date);

    Long countByProject(Optional<Project> project);
    
    List<Report> findByCountOrderByDateTimeDesc(int count);
    
    List<Report> findIdByCountOrderByDateTimeDesc(Optional<Project> project, int count);
    
    void remove(String id);
    
    Page<Report> findByProjectOrderByStartTimeDesc(Optional<Project> project, Pageable pageable);

    List<Report> findByCategoryNameList(Optional<Project> project, String name);

    List<WeeklyReportAggregation> findByPeriodicAggregation(Optional<Project> project, int daysPast);

}
