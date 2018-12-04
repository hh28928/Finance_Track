package com.example.hammadhanif.finance_tracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class SavingsTrackerActivity extends AppCompatActivity {
    ArrayList<goal> theGoals = new ArrayList<>();//This will hold the goals created by the user
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
        goalAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theGoals);
        theDisplay.setAdapter(goalAdapter);

        //These are for creating a dialog for deleting/editing goals
        theDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View theView, int position, long id){
                //After an item is clicked on, a dialog window will appear
                AlertDialog.Builder goalOptions = new AlertDialog.Builder(SavingsTrackerActivity.this);
                final EditText changeAmount = new EditText(SavingsTrackerActivity.this);
                final goal holdGoal = (goal) parent.getItemAtPosition(position);

                LinearLayout.LayoutParams editLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                changeAmount.setLayoutParams(editLayout);
                changeAmount.setHint("$0.00");
                goalOptions.setView(changeAmount);
                goalOptions.setMessage(parent.getItemAtPosition(position).toString());

                //This is intended for changing the goal amount
                goalOptions.setNegativeButton("Add to Current", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                        if(changeAmount.getText().toString().equals("")){//Checking if the editText is Empty
                            Toast.makeText(SavingsTrackerActivity.this,"Please enter a numeric value",Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(changeAmount.getText().toString().matches("^\\d*\\.?\\d*$")) {
                                Toast.makeText(SavingsTrackerActivity.this, "The value you entered contained a non-numeric character", LENGTH_LONG).show();
                                return;
                            }
                            else {
                                holdGoal.setCurrentAmount(holdGoal.getCurrentAmount() + Float.parseFloat(changeAmount.getText().toString()));
                            }
                        }
                        goalAdapter.notifyDataSetChanged();
                    }
                });

                goalOptions.setPositiveButton("Subtract from Current", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface,int i){
                        if(changeAmount.getText().toString().equals("")){//Checking if the editText is Empty
                            Toast.makeText(SavingsTrackerActivity.this,"Please enter a numeric value",Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(changeAmount.getText().toString().matches("^\\d*\\.?\\d*$")) {
                                Toast.makeText(SavingsTrackerActivity.this, "The value you entered contained a non-numeric character", LENGTH_LONG).show();
                                return;
                            }
                            else{
                                if(Float.parseFloat(changeAmount.getText().toString()) > holdGoal.getCurrentAmount()){
                                    Toast.makeText(SavingsTrackerActivity.this,"The amount entered is larger than what has been saved towards the goal",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    holdGoal.setCurrentAmount(holdGoal.getCurrentAmount() - Float.parseFloat(changeAmount.getText().toString()));
                                }
                            }
                        }
                        goalAdapter.notifyDataSetChanged();
                    }
                });

                goalOptions.setNeutralButton("Cancel",null);
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

        //This method is for deleting any goals after longpressing it
        theDisplay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                goalAdapter.remove(theGoals.get(position));
                goalAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }


    public void addGoal(View theView)   {
        if(!theGoals.isEmpty()) {
            Iterator<goal> goalIterator = theGoals.iterator();

            while(goalIterator.hasNext()) {
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

                if (!(goal_et.getText().toString().matches("^\\d*\\.?\\d*$")) && !(current_et.getText().toString().matches("^\\d*\\.?\\d*$"))) {//Checks if there are any non-float numbers in the goal and current fields
                    Toast.makeText(this, "One of the numeric field values contains a non-numeric character", LENGTH_LONG).show();
                    return;
                } else {
                    goal holdGoal = new goal(Float.parseFloat(goal_et.getText().toString()), Float.parseFloat(current_et.getText().toString()), name_et.getText().toString());
                    if (holdGoal.getCurrentAmount() >= holdGoal.getGoalAmount()) {
                        Toast.makeText(this, "The current amount you entered is equal to the goal amount, you don't need to make a goal", LENGTH_LONG).show();
                    } else {
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
    }
    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, MenuActivity.class);
        startActivity(backIntent);
    }

    public void forAddGoalTest(String theName, String theGoal, String theCurrent){
        //Uses strings instead of the edittexts, just makes sure they add properly or won't add if they don't meet the criteria
        if(!theGoals.isEmpty()) {
            Iterator<goal> goalIterator = theGoals.iterator();

            while(goalIterator.hasNext()) {
                //This loop is to make sure the goal name has not been used more than once
                goal currentGoal = goalIterator.next();
                if (currentGoal.goalName.equals(theName)) {
                    //Toast.makeText(this, "This goal name has already been used, please choose another", LENGTH_LONG).show();
                    return; //returns if the name has been used already
                }
            }
        }
        if (theName.equals("") || theGoal.equals("")||theCurrent.equals("")) {
            //Toast.makeText(this, "At least one field is empty", LENGTH_LONG).show();
            return;//Checks if either the goal amount or name field is empty
        }
        else {
            if(theName.length() > 30) {//Checking the length of the goal name
                //Toast.makeText(this, "The name of this goal is too long, it can only be thirty characters", LENGTH_LONG).show();
                return;
            }
            else {
                goal holdGoal = new goal(Float.parseFloat(theGoal), Float.parseFloat(theCurrent), theName);
                if(holdGoal.getCurrentAmount() >= holdGoal.getGoalAmount()) {
                    //Toast.makeText(this, "The current amount you entered is equal to the goal amount, you don't need to make a goal", LENGTH_LONG).show();
                }
                else {
                    theGoals.add(holdGoal);//Adds a new goal into the ArrayList, it should show up on the list UI
                    theGoal = ("");
                    theName = ("");
                    theCurrent = ("");
                }
                return;
            }
        }
    }

}



