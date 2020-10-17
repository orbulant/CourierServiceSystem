package org.aaaa.Enums;

public enum Roles {
    ManagingStaff("admin"),
    DeliveryStaff("delivery");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
