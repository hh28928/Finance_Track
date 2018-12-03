package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class WeeklySalaryActivity extends AppCompatActivity {

    EditText hours_et, rate_et, tax_et;
    TextView display;
    private DatabaseReference databaseReference; // store data to firebase
    public String yearly;
    String uidR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_salary);

        hours_et = findViewById(R.id.hours_worked);
        rate_et = findViewById(R.id.rate);
        tax_et = findViewById(R.id.percent);

        display = findViewById(R.id.display_tv);

        uidR = getIntent().getExtras().getString("UID"); // Users Authentication

        }

    public void onCalculate(View view){
        String hours_w = hours_et.getText().toString();
        String rate_w = rate_et.getText().toString();
        String tax_w = tax_et.getText().toString();

        if (hours_w.equals("") || rate_w.equals("") || tax_w.equals("")) {
            Toast.makeText(WeeklySalaryActivity.this, "At least one field is empty", Toast.LENGTH_LONG).show();
        } else {
            Float hours_convert = Float.parseFloat(hours_w);
            Float rate_convert = Float.parseFloat(rate_w);
            Float tax_convert = Float.parseFloat(tax_w);
            Float tax = tax_convert / 100;
            tax = tax * hours_convert * rate_convert;
            Float total = (hours_convert * rate_convert) - tax;
            String weekly = String.format("%.2f", total);

            display.setText("Your Calculated Weekly Salary is: " + weekly);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference data = database.getReference().child(uidR).child("budget");
            data.setValue(weekly);
            Toast.makeText(this, "Budget has been updated...", Toast.LENGTH_SHORT).show();
        }
    }

    public String calcSalaryForTesting(String hours_w, String rate_w, String tax_w) {
        Float hours_convert = Float.parseFloat(hours_w);
        Float rate_convert = Float.parseFloat(rate_w);
        Float tax_convert = Float.parseFloat(tax_w);
        Float tax = tax_convert / 100;
        tax = tax * hours_convert * rate_convert;
        Float total = (hours_convert * rate_convert) - tax;
        String weekly = String.format("%.2f", total);
        return weekly;
    }
}

