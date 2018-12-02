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
    }
}