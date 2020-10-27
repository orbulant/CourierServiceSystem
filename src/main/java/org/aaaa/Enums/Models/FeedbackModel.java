package org.aaaa.Enums.Models;

public enum FeedbackModel implements Models {
    FeedbackID(0),
    OrderID(1),
    Feedback(2),
    FeedbackDate(3),
    CreatedBy(4),
    CreatedOn(5),
    ChangedBy(6),
    ChangedOn(7);

    private final int index;

    FeedbackModel(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
