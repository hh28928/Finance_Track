package com.example.hammadhanif.finance_tracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BillReminderActivity extends AppCompatActivity {


    String stName, stDate;
    ListView listView;
    ArrayAdapter myAdapter;
    ArrayList<String> ToDO = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_reminder);

        listView = (ListView) findViewById(R.id.myList);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String Item = ((TextView) view).getText().toString();
                myAdapter.remove(Item);
                return true;
            }
        });

    }


    public void onClickAdd(View view) {
        Intent addIntent = new Intent(this, NotifyMeActivity.class);
        startActivityForResult(addIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            stName = data.getStringExtra("NAME");
            stDate = data.getStringExtra("DATE");
            String st_complete = stName + "  " + stDate;

            ToDO.add(st_complete);

            myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, ToDO);

            listView.setAdapter(myAdapter);
        }
        }
    }
