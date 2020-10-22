package org.aaaa.Enums.Models;

public enum UserModel implements Models {
    Username(0),
    Password(1),
    Role(2),
    AccountID(3);

    private final int index;

    UserModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
