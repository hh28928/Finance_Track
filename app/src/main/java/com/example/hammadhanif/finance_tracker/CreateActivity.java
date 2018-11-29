package com.example.hammadhanif.finance_tracker;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class CreateActivity extends AppCompatActivity {

    String stName, stAmount;
    String[] createBudgetColumns = {"Name", "Spent", "Remaining", "Salary"};
    String[][] createBudgetTables;
    TableView<String[]> tableView;
    int salary = 4400;
    int salaryCopy = salary;
    int ctr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        tableView = findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(Color.parseColor("#2D51A3"));

        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this, createBudgetColumns));

        tableView.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Toast.makeText(CreateActivity.this, ((String[])clickedData)[1], Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void populateData() {

        Spaceprobe spaceprobe = new Spaceprobe();
        ArrayList<Spaceprobe> spaceprobesList = new ArrayList<Spaceprobe>();

        spaceprobe.setName(stName);
        spaceprobe.setAmountSpent("$" + stAmount);
        spaceprobe.setSalary("$" + "4400");
        salaryCopy = salaryCopy - Integer.valueOf(stAmount);
        spaceprobe.setAmountRemaining("$" + Integer.toString(salaryCopy));
        spaceprobesList.add(spaceprobe);

        createBudgetTables = new String[spaceprobesList.size()][4];
        for (int i = 0; i < spaceprobesList.size(); i++) {
            Spaceprobe s = spaceprobesList.get(i);

            createBudgetTables[i][0] = spaceprobe.getName();
            createBudgetTables[i][1] = spaceprobe.getAmountSpent();
            createBudgetTables[i][2] = spaceprobe.getAmountRemaining();
            createBudgetTables[i][3] = spaceprobe.getSalary();
        }
        ctr++;
        tableView.setDataAdapter(new SimpleTableDataAdapter(this, createBudgetTables));
    }

    public void onClickAdd(View view) {
        Intent ExpenseIntent = new Intent(this, AddExpenseActivity.class);
        startActivityForResult(ExpenseIntent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            stName = data.getStringExtra("NAME");
            stAmount = data.getStringExtra("AMOUNT");
            populateData();

        }
        }
}
