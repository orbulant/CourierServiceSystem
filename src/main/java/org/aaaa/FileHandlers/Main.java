package org.aaaa.FileHandlers;

import org.aaaa.CurrentUser;
import org.aaaa.Enums.DatabasePath;
import org.aaaa.Staff;

public class Main {
    public static void main(String[] args)
    {
        FileHandler fh = new FileHandler(DatabasePath.Staff.getName());

        //INPUT
        String username = "vydranix@gmail.com";
        String password = "testingpassword1";

        Staff TESTINGSTAFF = new Staff(username, password);
        fh.currUser(fh.loginSuccess(TESTINGSTAFF), TESTINGSTAFF);

        System.out.println(CurrentUser.getStaff().getUsername() +"\n"+ CurrentUser.getStaff().getPassword() +"\n"+ CurrentUser.getStaff().getRole() +"\n"+ CurrentUser.getStaff().getAccountID());
        System.out.println(CurrentUser.getStaff().getCreatedBy() +"\n"+ CurrentUser.getStaff().getCreatedOn() +"\n"+ CurrentUser.getStaff().getChangedBy() +"\n"+ CurrentUser.getStaff().getChangedOn());

    }

}
