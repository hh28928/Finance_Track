package com.example.hammadhanif.finance_tracker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NotifyMeActivity extends AppCompatActivity {

    Calendar myCalendar;
    EditText Notify_date, name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_me);

        Notify_date = findViewById(R.id.edit_date);
        name = findViewById(R.id.edit_name);
        myCalendar = Calendar.getInstance();

    }

    public void calendarPopup(View view) {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        new DatePickerDialog(NotifyMeActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Notify_date.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickAdd(View view) {
        String nameSt = name.getText().toString();
        String dateSt = Notify_date.getText().toString();

        if (nameSt.equals("") || dateSt.equals("")) {
            Toast.makeText(NotifyMeActivity.this, "At least one of the fields is empty!", Toast.LENGTH_LONG).show();
        } else {
            passData(nameSt, dateSt);
            name.setText("");
            Notify_date.setText("");
        }
    }

    private void passData(String nameSt, String dateSt) {
        Intent saveIntent = new Intent();
        saveIntent.putExtra("NAME", nameSt);
        saveIntent.putExtra("DATE", dateSt);
        setResult(Activity.RESULT_OK, saveIntent);
        finish();
    }
}
