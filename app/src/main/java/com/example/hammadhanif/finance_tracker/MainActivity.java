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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button Login;
    private Button userRegistration;
    private ProgressDialog progressDialog;
    private EditText Username, Password;
    private TextView Info;
    private int counter = 5;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUIViews();

        Info.setText("No of attempts remaining: 5");

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = mAuth.getCurrentUser();


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();
                Log.d("TAG", "Username is: " + user + " Password is: " + pass);
                validate(user, pass);
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registration_Activity.class));
            }
        });
    }

    private void setupUIViews(){
        Username = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.login);
        userRegistration = (Button)findViewById(R.id.register);
    }

    private void validate(final String userName, String userPassword) {
        if (userName.equals("") || userPassword.equals("")) {
            Toast.makeText(this, "Either Username or Password not entered!", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Intent loggedIN = new Intent(MainActivity.this, MenuActivity.class);
                    loggedIN.putExtra("USERNAME", userName);
                    startActivity(loggedIN);
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    Info.setText("No of attempts remaining: " + counter);
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }
}
