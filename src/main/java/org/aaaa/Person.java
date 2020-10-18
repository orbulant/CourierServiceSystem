package org.aaaa;

import java.time.LocalDate;

public class Person extends Data {
    protected String accountID;
    protected String nric;
    protected String name;
    protected String housenum;
    protected String contactnum;
    protected LocalDate dob;

    protected Address fulladdress;

    public Person() {
        this.accountID = "";
        this.name = "";
        this.contactnum = "";
    }

    public Person(String name, String contactnum) {
        this.name = name;
        this.contactnum = contactnum;
    }

    public Person(String accountID, String name, String contactnum) {
        this.accountID = accountID;
        this.name = name;
        this.contactnum = contactnum;
    }

    public Person(String accountID, String name, String contactnum, String nric, LocalDate dob) {
        this.accountID = accountID;
        this.name = name;
        this.contactnum = contactnum;
        this.nric = nric;
        this.dob = dob;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHousenum() {
        return housenum;
    }

    public void setHousenum(String housenum) {
        this.housenum = housenum;
    }

    public String getContactNum() {
        return contactnum;
    }

    public void setContactNum(String contactnum) {
        this.contactnum = contactnum;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Address getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(Address fulladdress) {
        this.fulladdress = fulladdress;
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {
        
    }
}
