package org.aaaa;

import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.UserModel;
import org.aaaa.FileHandlers.FileHandlerAccount;

public class Staff implements DataInterface {
    private String username;
    private String password;
    private String role;
    private Person person;

    public Staff() {
        this.username = "";
        this.password = "";
        this.role = "";
    }

    public Staff(List<String> data) {
        this.set(data);
    }

    public List<String> get() {
        List<String> result = new ArrayList<>();

        result.add(this.username);
        result.add(this.password);
        result.add(this.role);
        result.add(this.person.getAccountID());

        return result;
    }

    public void set(List<String> data) {
        this.username = data.get(UserModel.Username.getIndex());
        this.password = data.get(UserModel.Password.getIndex());
        this.role = data.get(UserModel.Role.getIndex());
        this.person = new FileHandlerAccount(DatabasePath.Account.getName()).getAccountByID(data.get(UserModel.AccountID.getIndex()));
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
        return this.person.getAccountID();
    }

    public void setAccountID(String accountID) {
        this.person.setAccountID(accountID);
    }

    public Person getPerson() {return this.person;}
}
