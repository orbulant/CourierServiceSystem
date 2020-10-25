package org.aaaa.Enums.Models;

public enum DeliveryCancellationModel implements Models {
    OrderID(0),
    AccountID(1),
    Reason(2),
    Status(3),
    RequestDate(4),
    ApprovalDate(5);

    private final int index;

    DeliveryCancellationModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
