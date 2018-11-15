package com.example.hammadhanif.finance_tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SalaryActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button submit;
    private Button back;
    private RadioButton hourly;
    private RadioButton monthly;
    private EditText rateInput;
    private TextView rate;
    RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatesalary);

        setupUIViews();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                double num = Double.parseDouble(rateInput.getText().toString());
                double hourYearly = num * 40 * 52;
                String hYear = String.format("$%,.2f", hourYearly);
                double monthYearly = num * 12;
                String mYear = String.format("$%,.2f", monthYearly);
                rate.setText(""+radioId);

                if(radioButton.getText().equals("Hourly Rate")){
                    rate.setText(hYear);
                }
                else {
                    rate.setText(mYear);
                    }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SalaryActivity.this, MenuActivity.class));
            }
        });


    }

    private void setupUIViews(){
        rateInput = (EditText)findViewById(R.id.rateInput);
        monthly = (RadioButton)findViewById(R.id.monthly);
        rate = (TextView)findViewById(R.id.earnings);
        hourly = (RadioButton)findViewById(R.id.hourly);
        submit = (Button)findViewById(R.id.submit);
        back = (Button)findViewById(R.id.backbtn);
        radioGroup = (RadioGroup)findViewById(R.id.RG);
    }
}