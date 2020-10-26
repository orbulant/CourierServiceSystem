package org.aaaa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aaaa.Enums.Models.AccountModel;

public class Person extends Data {
    private String accountID;
    private String nric;
    private String name;
    private String housenum;
    private String contactnum;
    private LocalDate dob;

    private Address fulladdress;

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

    public Person(List<String> data) {
        this.set(data);
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public List<String> get() {
        List<String> result = new ArrayList<>();

        result.add(this.accountID);
        result.add(this.name);
        result.add(this.contactnum);
        result.add(this.nric);
        result.add(this.dob.toString());
        result.addAll(this.fulladdress.get());

        return result;
    }

    @Override
    public void set(List<String> data) {
        this.accountID   = data.get(AccountModel.AccountID.getIndex());
        this.name        = data.get(AccountModel.Name.getIndex());
        this.contactnum  = data.get(AccountModel.Contact.getIndex());
        this.nric        = data.get(AccountModel.NRIC.getIndex());
        this.dob         = LocalDate.parse(data.get(AccountModel.DOB.getIndex()));
        this.fulladdress = new Address(new String[]{
            data.get(AccountModel.Address.getIndex()),
            data.get(AccountModel.City.getIndex()),
            data.get(AccountModel.Postcode.getIndex()),
            data.get(AccountModel.State.getIndex()),
            data.get(AccountModel.Country.getIndex()),
        });
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
}
