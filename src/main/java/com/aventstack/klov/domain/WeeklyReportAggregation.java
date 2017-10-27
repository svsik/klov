package com.aventstack.klov.domain;

import java.util.Date;

public class WeeklyReportAggregation {

    private Long total;
    private Long passTotal;
    private Long skipTotal;
    private Date startTime;
    
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    
    public Long getPassTotal() {
        return passTotal;
    }
    public void setPassTotal(Long passTotal) {
        this.passTotal = passTotal;
    }
    
    public Long getSkipTotal() {
        return skipTotal;
    }
    public void setSkipTotal(Long skipTotal) {
        this.skipTotal = skipTotal;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
}
