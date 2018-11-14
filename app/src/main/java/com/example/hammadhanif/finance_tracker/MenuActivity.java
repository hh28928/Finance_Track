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

public class MenuActivity extends AppCompatActivity {

    String user_receive;
    private FirebaseAuth mAuth;
    private Button logout;
    TextView welcome;
    Button bt_Budget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bt_Budget = findViewById(R.id.cBudget);
        welcome = findViewById(R.id.welcome_text);
        mAuth = FirebaseAuth.getInstance();

        logout = (Button)findViewById(R.id.siOut);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });

        user_receive = getIntent().getExtras().getString("USERNAME");

        welcome.setText("WELCOME: " + user_receive);

        bt_Budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cIntent = new Intent(MenuActivity.this, CreateActivity.class);
                startActivity(cIntent);
            }
        });
    }

    private void Logout(){
        mAuth.signOut();
        finish();
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
    }

}
