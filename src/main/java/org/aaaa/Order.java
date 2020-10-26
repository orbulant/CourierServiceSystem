package org.aaaa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Models.OrderModel;
import org.aaaa.FileHandlers.FileHandlerAccount;
import org.aaaa.FileHandlers.FileHandlerOrder;

public class Order extends Data {
    private String orderID;
    private String order_name;
    private String order_desc;
    private String status;
    private LocalDate order_date;
    private LocalDate deli_date;
    private boolean is_fragile;
    private boolean auto_assign;
    private Address address;
    private Person person;
    private Person assigned_to;

    private FileHandlerOrder fileHandler = new FileHandlerOrder(DatabasePath.Order.getName());

    public Order() {
        super();
    }

    public Order(List<String> data) {
        this.set(data);
    }

    @Override
    public void create() {
        this.setCreatedInfo();
        //create arraylist and write to file
        try{
            fileHandler.addContent(fileHandler.getContent(DatabasePath.Order.getDataLength()), this.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        this.setChangedInfo();
        //update arraylist and replace in file
        try{
            fileHandler.update(fileHandler.getContent(DatabasePath.Order.getDataLength()), this.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> get() {
        List<String> result = new ArrayList<>();

        result.add(this.orderID);
        result.add(this.order_name);
        result.add(this.order_desc);
        result.add(this.order_date.toString());
        result.add(this.deli_date != null ? this.deli_date.toString() : "");
        result.add(Boolean.toString(is_fragile));
        result.add(this.assigned_to.getAccountID());
        result.add(this.status);

        result.add(this.person.getName());
        result.add(this.person.getContactNum());

        result.addAll(this.address.get());
        result.addAll(this.getCreatedInfo());
        result.addAll(this.getChangedInfo());

        return result;
    }

    public void set(List<String> data) {
        this.orderID    = data.get(OrderModel.OrderID.getIndex());
        this.order_name = data.get(OrderModel.OrderName.getIndex());
        this.order_desc = data.get(OrderModel.OrderDesc.getIndex());
        this.status     = data.get(OrderModel.Status.getIndex());
        this.order_date = LocalDate.parse(data.get(OrderModel.OrderDate.getIndex()));
        this.is_fragile = Boolean.parseBoolean(data.get(OrderModel.IsFragile.getIndex()));
        
        if(!data.get(OrderModel.DeliveryDate.getIndex()).equals("")) {
            this.deli_date = LocalDate.parse(data.get(OrderModel.DeliveryDate.getIndex()));
        }

        this.address = new Address(new String[] {
            data.get(OrderModel.Address.getIndex()), 
            data.get(OrderModel.City.getIndex()), 
            data.get(OrderModel.Postcode.getIndex()), 
            data.get(OrderModel.State.getIndex()), 
            data.get(OrderModel.Country.getIndex())
        });

        this.person      = new Person(data.get(OrderModel.RecipientName.getIndex()), data.get(OrderModel.RecipientContact.getIndex()));
        this.assigned_to = new FileHandlerAccount(DatabasePath.Account.getName()).getAccountByID(data.get(OrderModel.AssignedTo.getIndex()));

        this.createdBy = data.get(OrderModel.CreatedBy.getIndex());
        this.changedBy = data.get(OrderModel.ChangedBy.getIndex());

        if(!data.get(OrderModel.CreatedOn.getIndex()).isBlank()) {
            this.createdOn = LocalDateTime.parse(data.get(OrderModel.CreatedOn.getIndex()));
        }

        if(!data.get(OrderModel.ChangedOn.getIndex()).isBlank()) {
            this.changedOn = LocalDateTime.parse(data.get(OrderModel.ChangedOn.getIndex()));
        }
    }

    public String getOrderName() {
        return order_name;
    }

    public void setOrderName(String order_name) {
        this.order_name = order_name;
    }

    public String getOrderDesc() {
        return order_desc;
    }

    public void setOrderDesc(String order_desc) {
        this.order_desc = order_desc;
    }

    public LocalDate getOrderDate() {
        return order_date;
    }

    public void setOrderDate(LocalDate order_date) {
        this.order_date = order_date;
    }

    public LocalDate getDeliDate() {
        return deli_date;
    }

    public void setDeliDate(LocalDate deli_date) {
        this.deli_date = deli_date;
    }

    public Person getAccount() {
        return person;
    }

    public void setAccount(Person person) {
        this.person = person;
    }

    public Person getAssignTo() {
        return this.assigned_to;
    }

    public void setAssignTo(Person assigned_to) {
        this.assigned_to = assigned_to;
    }

    public boolean isIsFragile() {
        return is_fragile;
    }

    public void setIsFragile(boolean is_fragile) {
        this.is_fragile = is_fragile;
    }

    public boolean isAutoAssign() {
        return auto_assign;
    }

    public void setAutoAssign(boolean auto_assign) {
        this.auto_assign = auto_assign;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
}
