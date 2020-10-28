package org.aaaa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.DeliveryCancellationModel;
import org.aaaa.FileHandlers.FileHandlerOrder;
import org.aaaa.FileHandlers.FileHandlerUser;

public class DeliveryCancellation implements DataInterface {
    private String orderID;
    private String accountID;
    private Order order;
    private Staff staff;
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
        this.set(data);
    }

    @Override
    public List<String> get() {
        List<String> result = new ArrayList<>();

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

    @Override
    public void set(List<String> data) {
        FileHandlerUser fileHandlerUser = new FileHandlerUser(DatabasePath.Staff.getName());
        this.staff = fileHandlerUser.getUserByAccountID(data.get(DeliveryCancellationModel.AccountID.getIndex()));
        FileHandlerOrder fileHandlerOrder = new FileHandlerOrder(DatabasePath.Order.getName());
        this.order = fileHandlerOrder.assignOrder(fileHandlerOrder.getOrderByID(data.get(DeliveryCancellationModel.OrderID.getIndex())));

        this.orderID      = data.get(DeliveryCancellationModel.OrderID.getIndex());
        this.accountID    = data.get(DeliveryCancellationModel.AccountID.getIndex());
        this.reason       = data.get(DeliveryCancellationModel.Reason.getIndex());
        this.status       = data.get(DeliveryCancellationModel.Status.getIndex());
        this.requestDate  = LocalDateTime.parse(data.get(DeliveryCancellationModel.RequestDate.getIndex()));

        if(!data.get(DeliveryCancellationModel.ApprovalDate.getIndex()).equals("")) {
            this.approvalDate = LocalDateTime.parse(data.get(DeliveryCancellationModel.ApprovalDate.getIndex()));
        }
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
