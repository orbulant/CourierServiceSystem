package org.aaaa.Enums;

public enum Status {
    Processing("Processing"),
    Canceled("Canceled"),
    Delivering("Delivering"),
    Delivered("Delivered");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
