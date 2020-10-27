package org.aaaa.Enums;

public enum Status {
    Processing("Processing"),
    Cancelled("Cancelled"),
    Delivering("Delivering"),
    Delivered("Delivered"),
    Approved("Approved"),
    Rejected("Rejected");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
