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

public class Registration_Activity extends AppCompatActivity {

    EditText edit_full_name, edit_email, edit_pass, edit_address, edit_city, edit_state;
    EditText edit_zipcode, edit_country, edit_phone_number;
    Button btnregister, btnback;
    String email, name, password;
    private FirebaseAuth mAuth;
    //private FirebaseStorage firebaseStorage;
    //private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupUIViews();

        mAuth = FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = edit_email.getText().toString().trim();
                    String user_password = edit_pass.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                //sendEmailVerification();
                                //sendUserData();
                                //firebaseAuth.signOut();
                                Toast.makeText(Registration_Activity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(Registration_Activity.this, MainActivity.class));
                            }else{
                                Toast.makeText(Registration_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration_Activity.this, MainActivity.class));
            }
        });

    }



    private void setupUIViews(){
        //edit_name = (EditText)findViewById(R.id.edit_full_name);
        edit_pass = (EditText)findViewById(R.id.password);
        edit_email = (EditText)findViewById(R.id.email);
        btnregister = (Button)findViewById(R.id.registration);
        btnback = (Button)findViewById(R.id.back);

    }

    private Boolean validate(){
        Boolean result = false;

        //name = edit_name.getText().toString();
        password = edit_pass.getText().toString();
        email = edit_email.getText().toString();


        if(/*name.isEmpty() ||*/ password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }
}

   /* private void registerUser() {
        String email = edit_email.getText().toString().trim();
        String password = edit_pass.getText().toString().trim();

        if (email.isEmpty()) {
            edit_email.setError("Email is required");
            edit_email.requestFocus();
            return;
        }

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

            case R.id.back:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}
*/