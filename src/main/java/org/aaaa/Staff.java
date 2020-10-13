package org.aaaa;

import java.time.LocalDate;
//*accountID,email,password,role,createdBy,createdOn,changedBy,changedOn*
public class Staff extends Person {
    protected String username;
    protected String password;
    protected String role;
    protected String accountID;
    protected String createdBy;
    protected LocalDate createdOn;
    protected String changedBy;
    protected LocalDate changedOn;


    public Staff() {
        this.username = "";
        this.password = "";
        this.role = "";
    }

    public Staff(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public LocalDate getChangedOn() {
        return changedOn;
    }

    public void setChangedOn(LocalDate changedOn) {
        this.changedOn = changedOn;
    }
}
