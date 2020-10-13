package org.aaaa;

import java.time.LocalDateTime;

public class Data {
    protected String createdBy; // get name using User.username
    protected LocalDateTime createdOn;
    protected String changedBy;
    protected LocalDateTime changedOn;

    public void create() {
        // do something
    }

    public void edit() {
        // do something
    }

    public void delete() {
        // do something
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDateTime getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(LocalDateTime changedOn) {
        this.changedOn = changedOn;
    }    
} 
