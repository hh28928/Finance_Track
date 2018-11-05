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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration_Activity extends AppCompatActivity {

    EditText edit_full_name, edit_email, edit_pass, edit_address, edit_city, edit_state;
    EditText edit_zipcode, edit_country, edit_phone_number;
    Button button_register;

    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edit_full_name = findViewById(R.id.name);
        edit_email = findViewById(R.id.email);
        edit_pass = findViewById(R.id.password);
        edit_address =findViewById(R.id.address);
        edit_city = findViewById(R.id.city);
        edit_state = findViewById(R.id.state);
        edit_zipcode = findViewById(R.id.zip_code);
        edit_country = findViewById(R.id.country);
        edit_phone_number = findViewById(R.id.phone);
        progressDialog = new ProgressDialog(this);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        button_register = findViewById(R.id.registration);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
    }

    //on click registration
    public void onClickRegister() {


        progressDialog.setMessage("Signing In");
        String email_string = edit_email.getText().toString();
        String password_string = edit_pass.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email_string, password_string ).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent signIn_Intent = new Intent(Registration_Activity.this, MainActivity.class);
                            startActivity(signIn_Intent);
                        } else {
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration_Activity.this, "User registered Successfully!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                    }
                }
        });
    }
}
