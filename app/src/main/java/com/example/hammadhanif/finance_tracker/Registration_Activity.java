package com.example.hammadhanif.finance_tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_full_name, edit_email, edit_pass, edit_address, edit_city, edit_state;
    EditText edit_zipcode, edit_country, edit_phone_number;
    //ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edit_email = findViewById(R.id.email);
        edit_pass = findViewById(R.id.password);

        //progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registration).setOnClickListener(this);
        findViewById(R.id.loginR).setOnClickListener(this);
    }

    private void registerUser() {
        String email = edit_email.getText().toString().trim();
        String password = edit_pass.getText().toString().trim();

        if (email.isEmpty()) {
            edit_email.setError("Email is required");
            edit_email.requestFocus();
            return;
        }

        /*if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edit_email.setError("Please enter a valid email");
            edit_email.requestFocus();
            return;
        }
*/
        if (password.isEmpty()) {
            edit_pass.setError("Email is required");
            edit_pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            edit_pass.setError("Minimum lenght of password should be 6");
            edit_pass.requestFocus();
            return;
        }

        //progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(getApplicationContext(), "User Registered Successfull", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();

                }

                // ...
            }
        });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registration:
                registerUser();
                break;

            case R.id.loginR:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}