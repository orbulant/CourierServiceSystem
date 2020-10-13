package org.aaaa;

import java.util.Calendar;

public class Data {
    protected String createdBy; // get name using User.username
    protected Calendar createdOn;
    protected String changedBy;
    protected Calendar changedOn;

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

    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public Calendar getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(Calendar changedOn) {
        this.changedOn = changedOn;
    }    
} 
