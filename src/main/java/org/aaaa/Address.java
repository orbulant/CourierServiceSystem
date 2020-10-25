package org.aaaa;

import java.util.ArrayList;
import java.util.List;

public class Address implements DataInterface {
    private String address;
    private String city;
    private String postcode;
    private String state;
    private String country;

    public Address() {
        this.address  = "";
        this.city     = "";
        this.postcode = "";
        this.state    = "";
        this.country  = "";
    }

    public Address(String[] address) {
        this.address  = address[0];
        this.city     = address[1];
        this.postcode = address[2];
        this.state    = address[3];
        this.country  = address[4];
    }

    @Override
    public List<String> get() {
        ArrayList<String> result = new ArrayList<>();

        result.add(this.address);
        result.add(this.city);
        result.add(this.postcode);
        result.add(this.state);
        result.add(this.country);

        return result;
    }

    @Override
    public void set(List<String> data) {
        this.address  = data.get(0);
        this.city     = data.get(1);
        this.postcode = data.get(2);
        this.state    = data.get(3);
        this.country  = data.get(4);
    }

    public String toString() {
        return String.join(",", new String[] { address, city, postcode, state, country });
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
