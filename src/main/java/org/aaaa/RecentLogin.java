package org.aaaa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.RecentLoginModel;
import org.aaaa.FileHandlers.FileHandler;
import org.aaaa.FileHandlers.FileHandlerUser;

public class RecentLogin implements DataInterface {
    private Staff staff;
    private LocalDateTime loginDateTime;
    private FileHandler fileHandler;

    public RecentLogin() {
        this.fileHandler = new FileHandler(DatabasePath.RecentLogin.getName());
    }

    public RecentLogin(List<String> data) {
        this.set(data);
    }

    public void create() {
        List<String> temp =  new ArrayList<>();
        temp.add(CurrentUser.getStaff().getAccountID());
        temp.add(LocalDateTime.now().toString());
        //create arraylist and write to file
        try{
            fileHandler.addContent(fileHandler.getContent(DatabasePath.RecentLogin.getDataLength()), temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        List<String> result = new ArrayList<>();

        result.add(this.staff.getAccountID());
        result.add(this.loginDateTime.toString());

        return result;
    }

    @Override
    public void set(List<String> data) {
        this.staff = new FileHandlerUser(DatabasePath.Staff.getName()).getUserByAccountID(data.get(RecentLoginModel.AccountID.getIndex()));
        this.loginDateTime = LocalDateTime.parse(data.get(RecentLoginModel.LoginDateTime.getIndex()));
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDateTime getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(LocalDateTime loginDateTime) {
        this.loginDateTime = loginDateTime;
    }
}
