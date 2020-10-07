package org.aaaa;

// Class for setting current user of system
public class CurrentUser {
    private static Staff staff;
 
    // Use this first
    public CurrentUser(Staff staff) {
        CurrentUser.staff = staff;
    }

    public static Staff getStaff() {
        return staff;
    }

    public static void setStaff(Staff staff) {
        CurrentUser.staff = staff;
    }
}
