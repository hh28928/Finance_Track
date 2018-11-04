package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickReg(View view) {
        Intent regIntent = new Intent(this, Registration_Activity.class);
        startActivity(regIntent);
    }
}
