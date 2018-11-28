package com.example.hammadhanif.finance_tracker;

//import android.app.ListActivity;
//import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Iterator;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class SavingsTrackerActivity extends AppCompatActivity {
    ArrayList<goal> theGoals = new ArrayList<goal>();//This will hold the goals created by the user
    ArrayAdapter<goal> goalAdapter;

    EditText name_et, goal_et, current_et;
    ListView theDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings_tracker);
        //These are used for creating the financial goal for the listview
        name_et = findViewById(R.id.theName);
        goal_et = findViewById(R.id.goalAmount);
        current_et = findViewById(R.id.currentAmount);

        //Buttons for use in the project
        Button addGoal =  findViewById(R.id.addGoal);

        //These are for the display and putting items in the ListView
        theDisplay = findViewById(R.id.goalList);
        goalAdapter=new ArrayAdapter<goal>(this,android.R.layout.simple_list_item_1,theGoals);
        theDisplay.setAdapter(goalAdapter);

        //These are for creating a dialog for deleting/editing goals
        theDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View theView, int position, long id){
                //After an item is clicked on, a dialog window will appear
                AlertDialog.Builder goalOptions = new AlertDialog.Builder(SavingsTrackerActivity.this);
                LayoutInflater theInflater = getLayoutInflater();

                final EditText changeAmount = new EditText(SavingsTrackerActivity.this);
                final goal holdGoal = (goal) parent.getItemAtPosition(position);

                LinearLayout.LayoutParams editLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                changeAmount.setLayoutParams(editLayout);
                goalOptions.setView(changeAmount);
                goalOptions.setMessage(parent.getItemAtPosition(position).toString());
                //This is intended for changing the goal amount
                goalOptions.setNeutralButton("Add to Current", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                        if(changeAmount.getText().equals("")){//Checking if the editText is Empty
                            Toast.makeText(SavingsTrackerActivity.this,"Please enter a numerical value",Toast.LENGTH_LONG).show();
                        }
                        else{
                            holdGoal.setCurrentAmount(holdGoal.getCurrentAmount() + Float.parseFloat(changeAmount.getText().toString()));
                        }
                        goalAdapter.notifyDataSetChanged();
                    };
                });

                goalOptions.setPositiveButton("Subtract from Current", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                        if(changeAmount.getText().equals("")){//Checking if the editText is Empty
                            Toast.makeText(SavingsTrackerActivity.this,"Please enter a numerical value",Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(Float.parseFloat(changeAmount.getText().toString()) > holdGoal.getCurrentAmount()){
                                Toast.makeText(SavingsTrackerActivity.this,"The amount entered is larger than what has been saved towards the goal",Toast.LENGTH_LONG).show();
                            }
                            else{
                                holdGoal.setCurrentAmount(holdGoal.getCurrentAmount() - Float.parseFloat(changeAmount.getText().toString()));
                            }
                        }
                        goalAdapter.notifyDataSetChanged();
                    };
                });

                goalOptions.setNegativeButton("Cancel",null);
                goalOptions.show();
            }
        });

        //Various assorted methods below

        //This method is for adding goalss
        OnClickListener goalAdder = new OnClickListener(){
            @Override
            public void onClick(View theView){
                addGoal(theView);
                goalAdapter.notifyDataSetChanged();
            }
        };
        addGoal.setOnClickListener(goalAdder);


        OnClickListener goalDeleter = new OnClickListener(){
            @Override
            public void onClick(View theView){//This will have the user select and remove various goals they no longer need
                deleteGoal(theView);
                goalAdapter.notifyDataSetChanged();
            }
        };
    }
    public void deleteGoal(View theView){
        SparseBooleanArray selectedGoals = theDisplay.getCheckedItemPositions();
        int goalCount = theDisplay.getCount();
        if(goalCount > 0) {
            for (int i = goalCount - 1; i >= 0; i = i - 1) {
                if (selectedGoals.get(i) == true) {
                    goalAdapter.remove(theGoals.get(i));
                }
            }
        }
        else {
            Toast.makeText(this,"You need to select a goal for deletion", LENGTH_LONG).show();
        }
    }
    public void addGoal(View theView)   {
        if(theGoals.isEmpty() == false) {
            Iterator<goal> goalIterator = theGoals.iterator();

            while (goalIterator.hasNext()) {
                //This loop is to make sure the goal name has not been used more than once
                goal currentGoal = goalIterator.next();
                if (currentGoal.goalName.equals(name_et.getText().toString())) {
                    Toast.makeText(this, "This goal name has already been used, please choose another", LENGTH_LONG).show();
                    return; //returns if the name has been used already
                }
            }
        }
        if (name_et.getText().toString().equals("") || goal_et.getText().toString().equals("")||current_et.getText().toString().equals("")) {
            Toast.makeText(this, "At least one field is empty", LENGTH_LONG).show();
            return;//Checks if either the goal amount or name field is empty
        }
        else {
            if(name_et.getText().toString().length() > 30) {//Checking the length of the goal name
                Toast.makeText(this, "The name of this goal is too long, it can only be thirty characters", LENGTH_LONG).show();
                return;
            }
            else {
                goal holdGoal = new goal(Float.parseFloat(goal_et.getText().toString()), Float.parseFloat(current_et.getText().toString()), name_et.getText().toString());
                if(holdGoal.getCurrentAmount() >= holdGoal.getGoalAmount()) {
                    Toast.makeText(this, "The current amount you entered is equal to the goal amount, you don't need to make a goal", LENGTH_LONG).show();
                }
                else {
                    theGoals.add(holdGoal);//Adds a new goal into the ArrayList, it should show up on the list UI
                    goal_et.setText("");
                    name_et.setText("");
                    current_et.setText("");
                }
                goalAdapter.notifyDataSetChanged();
                return;
            }
        }
    }
    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, MenuActivity.class);
        startActivity(backIntent);
    }
}



