package com.example.hammadhanif.finance_tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddExpenseActivity extends AppCompatActivity {

    EditText amount, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        amount = findViewById(R.id.edit_spent);
        name = findViewById(R.id.edit_name);
    }

    public void onClickAdd(View view) {
        String nameSt = name.getText().toString();
        String amountSt = amount.getText().toString();

        if (nameSt.equals("") || amountSt.equals("")) {
            Toast.makeText(AddExpenseActivity.this, "At least one of the fields is empty!", Toast.LENGTH_LONG).show();
        } else {
            passData(nameSt, amountSt);
            name.setText("");
            amount.setText("");
        }
    }

    private void passData(String nameSt, String amountSt) {
        Intent saveIntent = new Intent();
        saveIntent.putExtra("NAME", nameSt);
        saveIntent.putExtra("AMOUNT", amountSt);
        setResult(Activity.RESULT_OK, saveIntent);
        finish();
    }
}
