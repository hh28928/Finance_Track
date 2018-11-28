package com.example.hammadhanif.finance_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Iterator;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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

        Button back = (Button) findViewById(R.id.back);
        Button addGoal = (Button) findViewById(R.id.addGoal);

        OnClickListener goalAdder = new OnClickListener(){
            @Override
            public void onClick(View theView){
                addGoal(theView);
                goalAdapter.notifyDataSetChanged();
            }
        };

        OnClickListener goalDeleter = new OnClickListener(){
            @Override
            public void onClick(View theView){//This will have the user select and remove various goals they no longer need
                SparseBooleanArray selectedGoals = theDisplay.getCheckedItemPositions();
                int goalCount = theDisplay.getCount();
                for(int i = goalCount - 1;i >= 0; i = i - 1){
                    if(selectedGoals.get(i)==true){
                        goalAdapter.remove(theGoals.get(i));
                    }
                }
                goalAdapter.notifyDataSetChanged();
            }
        };
        theDisplay = findViewById(R.id.goalList);
        goalAdapter=new ArrayAdapter<goal>(this,android.R.layout.simple_list_item_1,theGoals);
    }

    public void addGoal(View theView)   {
        Iterator<goal> goalIterator = theGoals.iterator();

        while(goalIterator.hasNext()) {
            //This loop is to make sure the goal name has not been used more than once
            goal currentGoal = goalIterator.next();
            if(currentGoal.goalName.equals(name_et.toString())) {
                Toast.makeText(this, "This goal name has already been used, please choose another", Toast.LENGTH_LONG).show();
                return; //returns if the name has been used already
            }
        }
        if(name_et.toString().length() > 30) {//Checking the length of the goal name
            Toast.makeText(this, "The name of this goal is too long, it can only be thirty characters", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            if (name_et.equals("") || goal_et.equals("")) {
                Toast.makeText(this, "At least one field is empty", Toast.LENGTH_LONG).show();
                return;//Checks if either the goal amount or name field is empty
            } else {
                Float currentVal = 0.0f;//If the current amount is empty, then zero is put in as the default value
                if (!current_et.equals(""))
                    currentVal = Float.parseFloat(current_et.toString());
                goal holdGoal = new goal(Float.parseFloat(goal_et.toString()), currentVal, name_et.toString());
                theGoals.add(holdGoal);//Adds a new goal into the ArrayList, it should show up on the list UI
            }
        }
    }

    public void addToGoal(View theView){
        //Adds money to the current amount for the selected goal

        goalAdapter.notifyDataSetChanged();
    }

    public void subtractFromGoal(View theView){
        //Subtracts money from the current amount for the selected goal

        goalAdapter.notifyDataSetChanged();
    }

    public void onClickBack(View view) {
        Intent backIntent = new Intent(this, SalaryActivity.class);
        startActivity(backIntent);
    }
}

class goal {
    //An object specifically created for this list
    float goalAmount; //Holds amount to be reached for the goal to be met
    float currentAmount;//Holds the current amount
    boolean reached;//"true" if goal has been reached, "false" otherwise
    String goalName;//Name of the goal

    public goal(float theGoal, float theCurrent, String theName) {
        goalAmount = theGoal;
        currentAmount = theCurrent;
        goalName = theName;
    }

    public void goalMet() {
        if (goalAmount == currentAmount) {
            reached = true;
        }
    }

    //Getters and setters below
    public float getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(float currentAmount) {
        this.currentAmount = currentAmount;
    }

    public boolean getReached() {
        return reached;
    }
}

