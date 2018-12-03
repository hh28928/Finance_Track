package com.example.hammadhanif.finance_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeeklySalaryActivityTest {

    @Test
    public void calcSalaryForTesting() {
        String input_hours = "40";
        String tax = "5";
        String rate = "10";
        String expected = "380.00";
        String output;
        WeeklySalaryActivity weeklySalaryActivity = new WeeklySalaryActivity();
        output = weeklySalaryActivity.calcSalaryForTesting(input_hours, rate, tax);

        assertEquals(expected, output);

        System.out.println("Test1 " + true + " " + output);


        String test1 = "";
        input_hours = "60";
        tax = "15";
        rate = "20.25";
        expected = "1032.75";

        WeeklySalaryActivity weeklySalaryActivity1 = new WeeklySalaryActivity();
        test1 = weeklySalaryActivity1.calcSalaryForTesting(input_hours, rate, tax);

        assertEquals(expected, test1);

        System.out.println("Test2 " + true + " " + test1);


        String test2 = "";
        input_hours = "40";
        tax = "25";
        rate = "89.25";
        expected = "2677.50";

        WeeklySalaryActivity weeklySalaryActivity2 = new WeeklySalaryActivity();
        test2 = weeklySalaryActivity2.calcSalaryForTesting(input_hours, rate, tax);

        assertEquals(expected, test2);

        System.out.println("Test3 " + true + " " + test2);




    }
}