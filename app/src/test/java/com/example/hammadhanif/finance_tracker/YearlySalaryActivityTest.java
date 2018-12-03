package com.example.hammadhanif.finance_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class YearlySalaryActivityTest {

    @Test
    public void calcSalaryForTesting() {
        String input_monthly = "5430.20";
        String tax = "10.29";
        String expected = "4871.43";
        String output;
        YearlySalaryActivity yearlySalaryActivity = new YearlySalaryActivity();
        output = yearlySalaryActivity.calcSalaryForTesting(input_monthly, tax);
        assertEquals(expected, output);

        System.out.println("Test1 " + true + " " + output);

        input_monthly = "4000";
        tax = "10";
        expected = "3600.00";
        YearlySalaryActivity yearlySalaryActivity1 = new YearlySalaryActivity();
        output = yearlySalaryActivity1.calcSalaryForTesting(input_monthly, tax);

        assertEquals(expected, output);

        System.out.println("Test2 " + true + " " + output);

        input_monthly = "0";
        tax = "10";
        expected = "0.00";
        YearlySalaryActivity yearlySalaryActivity2 = new YearlySalaryActivity();
        output = yearlySalaryActivity2.calcSalaryForTesting(input_monthly, tax);

        assertEquals(expected, output);

        System.out.println("Test3 " + true + " " + output);
    }
}