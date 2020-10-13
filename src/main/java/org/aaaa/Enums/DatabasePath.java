package org.aaaa.Enums;

public enum DatabasePath {
    Order("src/main/resources/database/order.txt");

    private final String name;

    DatabasePath(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
