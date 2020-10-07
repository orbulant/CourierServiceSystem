package org.aaaa;

public class Staff extends Person {
    protected String username;
    protected String password;
    protected String role;

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
}
