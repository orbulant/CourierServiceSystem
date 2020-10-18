package org.aaaa;

import java.time.LocalDateTime;

abstract public class Data {
    protected String createdBy; // get name using User.username
    protected LocalDateTime createdOn;
    protected String changedBy;
    protected LocalDateTime changedOn;

    public Data() {
        this.createdBy = "";
        this.changedBy = "";
    }

    abstract public void create();
    abstract public void update();

    public void setCreatedInfo() {
        this.createdBy = CurrentUser.getStaff().username;
        this.createdOn = LocalDateTime.now();
    }

    public void setChangedInfo() {
        this.changedBy = CurrentUser.getStaff().username;
        this.changedOn = LocalDateTime.now();
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
