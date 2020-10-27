package org.aaaa.Enums.Models;

public enum RecentLoginModel implements Models {
    AccountID(0),
    LoginDateTime(1);

    private final int index;

    RecentLoginModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
