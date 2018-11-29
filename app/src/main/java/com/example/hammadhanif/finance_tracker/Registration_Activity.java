package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Registration_Activity extends AppCompatActivity {

    EditText edit_full_name, edit_email, edit_pass, edit_cpass, edit_street, edit_city, edit_state;
    EditText edit_zipcode, edit_country, edit_phone_number;
    Button btnregister, btnback;
    String email, name, password;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference; // store data to firebase
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        setupUIViews();

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()){
                    //Upload data to the database
                    String user_email = edit_email.getText().toString().trim();
                    String user_password = edit_pass.getText().toString().trim();

                    mAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                            }else{
                                Toast.makeText(Registration_Activity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Registration_Activity.this, "Error", Toast.LENGTH_SHORT).show();
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
        edit_pass = (EditText)findViewById(R.id.password);
        edit_cpass = (EditText)findViewById(R.id.cpassword);
        edit_email = (EditText)findViewById(R.id.email);

        // info storing in firebase
        edit_full_name = (EditText)findViewById(R.id.full_name);
        edit_street = (EditText)findViewById(R.id.street);
        edit_city = (EditText)findViewById(R.id.city);
        edit_state = (EditText)findViewById(R.id.state);
        edit_zipcode = (EditText)findViewById(R.id.zip_code);
        edit_country = (EditText)findViewById(R.id.country);


        btnregister = (Button)findViewById(R.id.registration);
        btnback = (Button)findViewById(R.id.back);

        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(Registration_Activity.this,R.id.full_name, "[a-zA-Z\\s]+",R.string.full_name_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.email,android.util.Patterns.EMAIL_ADDRESS,R.string.email_Err);
        // awesomeValidation.addValidation(Registration_Activity.this,R.id.phno, RegexTemplate.TELEPHONE,R.string.phoneerr);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.password,regexPassword,R.string.password_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.cpassword,R.id.password,R.string.cpassword_Err);
        //Address
        awesomeValidation.addValidation(Registration_Activity.this,R.id.street, "[0-9a-zA-Z\\s]+",R.string.address_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.city, "[a-zA-Z\\s]+",R.string.address_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.state, "[a-zA-Z\\s]+",R.string.address_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.zip_code, "[0-9\\s]+",R.string.address_Err);
        awesomeValidation.addValidation(Registration_Activity.this,R.id.country, "[a-zA-Z\\s]+",R.string.address_Err);

    }
    // writing data to database
    private void saveUserInformation(){

        String email = edit_email.getText().toString().trim();
        String name = edit_full_name.getText().toString().trim();
        String street = edit_street.getText().toString().trim();
        String city = edit_city.getText().toString().trim();
        String state = edit_state.getText().toString().trim();
        String zip = edit_zipcode.getText().toString().trim();
        String country = edit_country.getText().toString().trim();

        UserInformation userInformation = new UserInformation(email,name,street,city,state,zip,country);
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this,"Information Saved ...",Toast.LENGTH_SHORT).show();
    }

    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        saveUserInformation();
                        Toast.makeText(Registration_Activity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_LONG).show();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(Registration_Activity.this, MainActivity.class));
                    }else{
                        Toast.makeText(Registration_Activity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}