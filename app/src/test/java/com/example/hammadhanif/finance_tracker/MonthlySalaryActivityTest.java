package com.example.hammadhanif.finance_tracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class MonthlySalaryActivityTest {

    @Test
    public void calcSalaryForTesting() {
        String input_monthly = "5430.20";
        String tax = "10.29";
        String expected = "4871.43";
        String output;
        MonthlySalaryActivity monthlySalaryActivity = new MonthlySalaryActivity();
        output = monthlySalaryActivity.calcSalaryForTesting(input_monthly, tax);

        assertEquals(expected, output);
    }
}