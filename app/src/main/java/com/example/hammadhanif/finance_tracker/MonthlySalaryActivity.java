package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MonthlySalaryActivity extends AppCompatActivity {

    EditText rate_et, tax_et;
    TextView display;
    String uidR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_salary);

        rate_et = findViewById(R.id.rate);
        tax_et = findViewById(R.id.percent);

        display = findViewById(R.id.display_tv);
        uidR = getIntent().getExtras().getString("UID"); // Users Authentication
    }

    public void onCalculate(View view) {
        String rate_w = rate_et.getText().toString();
        String tax_w = tax_et.getText().toString();
        calcSalary(rate_w, tax_w);
    }

    public void calcSalary(String rate_w, String tax_w) {
        if (rate_w.equals("") || tax_w.equals("")) {
            Toast.makeText(this, "At least one field is empty", Toast.LENGTH_LONG).show();
            return;
        } else {
            Float rate_convert = Float.parseFloat(rate_w);
            Float tax_convert = Float.parseFloat(tax_w);
            Float tax = tax_convert / 100;
            tax = tax * rate_convert;
            Float total = rate_convert - tax;
            String monthly = String.format("%.2f", total);

            display.setText("Your Calculated Monthly Salary is: " + monthly);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference data = database.getReference().child(uidR).child("budget");
            data.setValue(monthly);
            Toast.makeText(this, "Budget has been updated...", Toast.LENGTH_SHORT).show();
        }
    }

    public String calcSalaryForTesting(String rate_w, String tax_w) {
        Float rate_convert = Float.parseFloat(rate_w);
        Float tax_convert = Float.parseFloat(tax_w);
        Float tax = tax_convert / 100;
        tax = tax * rate_convert;
        Float total = rate_convert - tax;
        String monthly = String.format("%.2f", total);
        return monthly;
    }
}
