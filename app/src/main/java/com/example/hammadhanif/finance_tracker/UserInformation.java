package com.example.hammadhanif.finance_tracker;

public class UserInformation {

    public String email;
    public String name;
    public String street;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String budget;
    public String balance;

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
        this.budget = "0";
        this.balance = "0";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String regTest(String email, String name, String street, String city, String state, String zip, String country) {
        setEmail(email);
        setName(name);
        setStreet(street);
        setCity(city);
        setState(state);
        setZip(zip);
        setCountry(country);
        return "" + getEmail() + "; " + getName() + ": " + getStreet() + " " + getCity() + ", " + getState() + " " + getZip() + " " + getCountry();

    }
}
