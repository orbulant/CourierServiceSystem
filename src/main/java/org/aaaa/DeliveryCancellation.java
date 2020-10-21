package org.aaaa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryCancellation {
    private String orderID;
    private String accountID;
    private String reason;
    private String status;
    private LocalDateTime requestDate;
    private LocalDateTime approvalDate;

    public DeliveryCancellation() {
        this.accountID = CurrentUser.getStaff().getAccountID();
        this.requestDate = LocalDateTime.now();
    }

    public DeliveryCancellation(String orderID, String reason, String status) {
        this.accountID = CurrentUser.getStaff().getAccountID();
        this.requestDate = LocalDateTime.now();
        this.orderID = orderID;
        this.reason = reason;
        this.status = status;
    }

    public DeliveryCancellation(List<String> data) {
        this.orderID = data.get(0);
        this.accountID = data.get(1);
        this.reason = data.get(2);
        this.status = data.get(3);
        if(!data.get(4).isBlank()) {
            this.requestDate = LocalDateTime.parse(data.get(4));
        }
        if(!data.get(5).isBlank()) {
            this.approvalDate = LocalDateTime.parse(data.get(5));
        }
    }

    public List<String> getDeliCancelAsArray() {
        ArrayList<String> result = new ArrayList<>();

        result.add(orderID);
        result.add(accountID);
        result.add(reason);
        result.add(status);

        if (requestDate == null) {
            result.add("");
        } else {
            result.add(requestDate.toString());
        }

        if (approvalDate == null) {
            result.add("");
        } else {
            result.add(approvalDate.toString());
        }

        return result;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(LocalDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }
}
