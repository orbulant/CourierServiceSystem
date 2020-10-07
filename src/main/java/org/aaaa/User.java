package org.aaaa;

public class User {
    private static String username;
    private static String password;
    private static String role;
    private static StaffManaging staff;
 
    // Use this first
    public User(String username, String password, String role) {
        User.username = username;
        User.password = password;
        User.role = role;
    }

    // WIP
    public User(String username, String password, String role, StaffManaging staff) {
        User.username = username;
        User.password = password;
        User.role = role;
        User.staff = staff;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        User.role = role;
    }

    public static StaffManaging getStaff() {
        return staff;
    }

    public static void setStaff(StaffManaging staff) {
        User.staff = staff;
    }
}
