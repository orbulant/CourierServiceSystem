package org.aaaa;

import java.time.LocalDate;

public class Order extends Data {
    private String order_name;
    private String order_desc;
    private String assign_to;
    private LocalDate order_date;
    private LocalDate deli_date;
    private boolean is_fragile;
    private boolean auto_assign;
    private Address address;
    private Person person;

    public Order() {

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

    public String getAssignTo() {
        return assign_to;
    }

    public void setAssignTo(String assign_to) {
        this.assign_to = assign_to;
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
}
