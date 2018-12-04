package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SalaryActivity extends AppCompatActivity {

    private Button next_bt;
    private RadioButton rb;
    private RadioGroup radioGroup;
    private String uidR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatesalary);

        setupUIViews();

        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRadioButton();
            }
        });

        uidR = getIntent().getExtras().getString("UID");
    }

    private void setupUIViews(){
        next_bt = (Button)findViewById(R.id.next);
        radioGroup = (RadioGroup)findViewById(R.id.RG);
    }

    public void onClickRadioButton() {
        int selected_id = radioGroup.getCheckedRadioButtonId();
        rb = (RadioButton)findViewById(selected_id);
        if (rb.getText().toString().equals("Weekly Rate")) {
            Intent weeklyIntent = new Intent(this, WeeklySalaryActivity.class);
            weeklyIntent.putExtra("UID", uidR); // Passes the Users Authentication to a class
            startActivity(weeklyIntent);
        } else if (rb.getText().toString().equals("Monthly Rate")) {
            Intent monthlyIntent = new Intent(this, MonthlySalaryActivity.class);
            monthlyIntent.putExtra("UID", uidR); // Passes the Users Authentication to a class
            startActivity(monthlyIntent);
        } else if (rb.getText().toString().equals("Yearly Rate")) {
            Intent yearlyIntent = new Intent(this, YearlySalaryActivity.class);
            yearlyIntent.putExtra("UID", uidR); // Passes the Users Authentication to a class
            startActivity(yearlyIntent);
        }
    }
}