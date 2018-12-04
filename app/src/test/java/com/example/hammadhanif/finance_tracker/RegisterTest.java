package com.example.hammadhanif.finance_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void regTest(){
        String email = "Student@gmu.edu";
        String name = "Student";
        String street = "1234 GMU DR";
        String city = "Fairfax";
        String state = "VA";
        String zip = "12345";
        String country = "USA";
        String expected = "" + email + "; " + name + ": " + street + " " + city + ", " + state + " " + zip + " " + country;
        String output;

        UserInformation newUser = new UserInformation();
        output = newUser.regTest(email, name, street, city, state, zip, country);
        assertEquals(expected, output);

        System.out.println("Test1 " + true + " " + output);

        email = "johndoe@gmu.edu";
        name = "john doe";
        street = "1234 GMU DR";
        city = "Fairfax";
        state = "VA";
        zip = "12345";
        expected = "" + email + "; " + name + ": " + street + " " + city + ", " + state + " " + zip + " " + country;

        UserInformation newUser2 = new UserInformation();
        output = newUser2.regTest(email, name, street, city, state, zip, country);
        assertEquals(expected, output);

        System.out.println("Test2 " + true + " " + output);
    }

}
