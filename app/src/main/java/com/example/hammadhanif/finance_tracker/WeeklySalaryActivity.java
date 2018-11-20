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

import java.util.HashMap;
import java.util.Map;

public class WeeklySalaryActivity extends AppCompatActivity {

    EditText hours_et, rate_et, tax_et;
    Button calculate_bt;
    TextView display;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference; // store data to firebase
    public String weekly;

    public WeeklySalaryActivity(){

    }

    public WeeklySalaryActivity(String weekly){
        this.weekly = weekly;
    }

    public String getWeekly() {
        return weekly;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_salary);

        hours_et = findViewById(R.id.hours_worked);
        rate_et = findViewById(R.id.rate);
        tax_et = findViewById(R.id.percent);

        display = findViewById(R.id.display_tv);

        calculate_bt = (Button)findViewById(R.id.calculate);

        calculate_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    //saveDataInoformation;
                    display.setText("Your Calculated Weekly Salary is: " + weekly);
                }

            }

        });


}

    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, SalaryActivity.class);
        startActivity(backIntent);
    }

/*
    // writing data to database
    private void saveDataInformation(){

        String week = display.getText().toString();

        WeeklySalaryActivity weeklySalaryActivity = new WeeklySalaryActivity(week);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("Salary Info").setValue(weeklySalaryActivity);
        Toast.makeText(WeeklySalaryActivity.this,"Information Saved ...",Toast.LENGTH_SHORT).show();
    }
*/
}

