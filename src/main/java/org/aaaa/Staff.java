package org.aaaa;

public class Staff extends Person {
    protected String username;
    protected String password;
    protected String role;
    protected String accountID;

    public Staff() {
        this.accountID = "";
        this.username = "";
        this.password = "";
        this.role = "";
    }

    public Staff(String accountID){
        this.accountID = accountID;
    }

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Staff(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Staff(String accountID, String username, String password, String role) {
        this.accountID = accountID;
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
}
