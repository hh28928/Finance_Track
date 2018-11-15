package com.example.hammadhanif.finance_tracker;

public class UserInformation {

    public String name;
    public String address;
    public String city;
    public String state;
    public String zip;
    public String country;

    public UserInformation(){

    }

    public UserInformation(String name, String address, String city, String state, String zip, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

}
