package org.aaaa.Enums;

public enum DatabasePath {
    Order("src/main/resources/database/order.txt", 19),
    Staff("src/main/resources/database/user.txt", 4),
    Account("src/main/resources/database/account.txt", 15),
    Feedback("src/main/resources/database/feedback.txt", 8),
    RecentLogin("src/main/resources/database/recentLogin.txt", 2),
    DeliveryCancellation("src/main/resources/database/deliveryCancellation.txt", 6);

    private String name;
    private int dataLength;

    DatabasePath(String name) {
        this.name = name;
    }

    DatabasePath(int dataLength) {
        this.dataLength = dataLength;
    }

    DatabasePath(String name, int dataLength) {
        this.name = name;
        this.dataLength = dataLength;
    }

    public String getName() {
        return this.name;
    }

    public int getDataLength() {
        return dataLength;
    }
}
