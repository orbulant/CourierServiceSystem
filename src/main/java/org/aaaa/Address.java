package org.aaaa;

public class Address {
    private String address;
    private String city;
    private String postcode;
    private String state;
    private String country;

    public Address() {
        this.address = "";
        this.city = "";
        this.postcode = "";
        this.state = "";
        this.country = "";
    }

    public Address(String[] address) {
        this.address = address[0];
        this.city = address[1];
        this.postcode = address[2];
        this.state = address[3];
        this.country = address[4];
    }

    public String toString() {
        return String.join(",", new String[]{address, city, postcode, state, country});
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
