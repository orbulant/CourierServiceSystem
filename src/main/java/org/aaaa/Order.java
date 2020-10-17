package org.aaaa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.DatabasePath;
import org.aaaa.Enums.Status;
import org.aaaa.FileHandlers.FileHandlerOrder;

public class Order extends Data {
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

    @Override
    public void create() {
        this.setCreatedInfo();
        System.out.println(this.getOrderAsArray());
        //create arraylist and write to file
        try{
            fileHandler.addContent(fileHandler.getContent(DatabasePath.Order.getDataLength()), this.getOrderAsArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getOrderAsArray() {
        ArrayList<String> result = new ArrayList<>();

        result.add(String.valueOf(Integer.parseInt(fileHandler.getLatestID()) + 1));
        result.add(order_name);
        result.add(order_desc);
        result.add(order_date.toString());
        result.add(deli_date.toString());
        result.add(Boolean.toString(is_fragile));
        // result.add(assigned_to.getAccountID()); assignto
        result.add(Status.Processing.getStatus());
        result.add(person.getName());
        result.add(person.getContactNum());
        result.add(address.getAddress());
        result.add(address.getCity());
        result.add(address.getPostcode());
        result.add(address.getState());
        result.add(address.getCountry());
        result.add(this.createdBy);
        result.add(this.createdOn == null ? "" : this.createdOn.toString());
        result.add(this.changedBy);
        result.add(this.changedOn == null ? "" : this.changedOn.toString());

        return result;
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

    // public String getAssignTo() {
    //     return assign_to;
    // }

    // public void setAssignTo(String assign_to) {
    //     this.assign_to = assign_to;
    // }

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
}
