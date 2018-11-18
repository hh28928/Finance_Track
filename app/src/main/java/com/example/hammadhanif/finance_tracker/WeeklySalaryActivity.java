package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WeeklySalaryActivity extends AppCompatActivity {

    EditText hours_et, rate_et, tax_et;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_salary);

        hours_et = findViewById(R.id.hours_worked);
        rate_et = findViewById(R.id.rate);
        tax_et = findViewById(R.id.percent);

        display = findViewById(R.id.display_tv);
    }

    public void onCalculate(View view) {
        String hours_w = hours_et.getText().toString();
        String rate_w = rate_et.getText().toString();
        String tax_w = tax_et.getText().toString();
        if (hours_w.equals("") || rate_w.equals("") || tax_w.equals("")) {
            Toast.makeText(this, "At least one field is empty", Toast.LENGTH_LONG).show();
            return;
        } else {
            Float hours_convert = Float.parseFloat(hours_w);
            Float rate_convert = Float.parseFloat(rate_w);
            Float tax_convert = Float.parseFloat(tax_w);
            Float tax = tax_convert / 100;
            tax = tax * hours_convert * rate_convert;
            Float total = (hours_convert * rate_convert) - tax;
            String controlDeci = String.format("%.2f", total);

            display.setText("Your Calculated Weekly Salary is: " + controlDeci);
        }
    }

    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, SalaryActivity.class);
        startActivity(backIntent);
    }
}

