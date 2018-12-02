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
    }
}