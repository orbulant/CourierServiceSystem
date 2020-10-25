package org.aaaa.Enums.Models;

public enum AccountModel implements Models {
    AccountID(0),
    NRIC(1),
    Name(2),
    Contact(3),
    DOB(4),
    HomeContact(5),
    Address(6),
    City(7),
    Postcode(8),
    State(9),
    Country(10),
    CreatedBy(11),
    CreatedOn(12),
    ChangedBy(13),
    ChangedOn(14);

    private final int index;

    AccountModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
