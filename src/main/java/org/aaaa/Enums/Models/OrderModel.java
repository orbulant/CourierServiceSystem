package org.aaaa.Enums.Models;

public enum OrderModel implements Models {
    OrderID(0),
    OrderName(1),
    OrderDesc(2),
    OrderDate(3),
    DeliveryDate(4),
    IsFragile(5),
    AssignedTo(6),
    Status(7),
    RecipientName(8),
    RecipientContact(9),
    Address(10),
    City(11),
    Postcode(12),
    State(13),
    Country(14),
    CreatedBy(15),
    CreatedOn(16),
    ChangedBy(17),
    ChangedOn(18);

    private final int index;

    OrderModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
