package com.example.hammadhanif.finance_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class NotifyMeActivity extends AppCompatActivity {

    Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_me);

        myCalendar = Calendar.getInstance();
    }

    public void calendarPopup(View view) {

    }

    public void onClickAdd(View view) {
    }
}
