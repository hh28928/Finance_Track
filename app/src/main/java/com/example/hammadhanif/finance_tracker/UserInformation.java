package com.example.hammadhanif.finance_tracker;

public class UserInformation {

    public String email;
    public String name;
    public String street;
    public String city;
    public String state;
    public String zip;
    public String country;

    public UserInformation(){

    }

    public UserInformation(String email, String name, String street, String city, String state, String zip, String country) {
        this.email = email;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getEmail(){
        return email;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
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
