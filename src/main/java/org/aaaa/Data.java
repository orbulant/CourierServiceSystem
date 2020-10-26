package org.aaaa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

abstract public class Data implements DataInterface {
    protected String createdBy; // get name using User.username
    protected LocalDateTime createdOn;
    protected String changedBy;
    protected LocalDateTime changedOn;

    abstract public void create();
    abstract public void update();

    public Data() {
        this.createdBy = "";
        this.changedBy = "";
    }

    public void setCreatedInfo() {
        this.createdBy = CurrentUser.getStaff().getUsername();
        this.createdOn = LocalDateTime.now();
    }

    public List<String> getCreatedInfo()  {
        ArrayList<String> result = new ArrayList<>();

        result.add(this.createdBy == null ? "" : this.createdBy);
        result.add(this.createdOn == null ? "" : this.createdOn.toString());

        return result;
    }

    public void setChangedInfo() {
        this.changedBy = CurrentUser.getStaff().getUsername();
        this.changedOn = LocalDateTime.now();
    }

    public List<String> getChangedInfo()  {
        ArrayList<String> result = new ArrayList<>();

        result.add(this.changedBy == null ? "" : this.changedBy);
        result.add(this.changedOn == null ? "" : this.changedOn.toString());
        
        return result;
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
