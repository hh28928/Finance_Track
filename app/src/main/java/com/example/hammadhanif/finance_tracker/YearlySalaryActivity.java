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

public class YearlySalaryActivity extends AppCompatActivity {

    EditText rate_et, tax_et;
    TextView display;
    String uidR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yearly_salary);

        rate_et = findViewById(R.id.rate);
        tax_et = findViewById(R.id.percent);

        display = findViewById(R.id.display_tv);

        uidR = getIntent().getExtras().getString("UID"); // Users Authentication
    }

    public void onCalculate(View view) {
        String rate_w = rate_et.getText().toString();
        String tax_w = tax_et.getText().toString();
        if (rate_w.equals("") || tax_w.equals("")) {
            Toast.makeText(this, "At least one field is empty", Toast.LENGTH_LONG).show();
            return;
        } else {
            Float rate_convert = Float.parseFloat(rate_w);
            Float tax_convert = Float.parseFloat(tax_w);
            Float tax = tax_convert / 100;
            Float rate = rate_convert * 12;
            tax = tax * rate;
            Float total = rate - tax;
            String yearly = String.format("%.2f", total);

            display.setText("Your Calculated Yearly Salary is: " + yearly);

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference data = database.getReference().child(uidR).child("budget");
            data.setValue(yearly);
            Toast.makeText(this, "Budget has been updated...", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, SalaryActivity.class);
        startActivity(backIntent);
    }

}
