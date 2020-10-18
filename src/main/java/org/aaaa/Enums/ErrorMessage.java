package org.aaaa.Enums;

public enum ErrorMessage {
    Mandatory("Please fill in all required fields!");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
