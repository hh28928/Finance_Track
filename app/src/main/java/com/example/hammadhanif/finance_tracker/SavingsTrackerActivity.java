package com.example.hammadhanif.finance_tracker;

//import android.app.ListActivity;
//import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import java.util.ArrayList;
import java.util.Iterator;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

        //Buttons for use in the project
        Button addGoal =  findViewById(R.id.addGoal);
        Button deletBtn = findViewById(R.id.deleteGoal);

        //These are for the display and putting items in the ListView
        theDisplay = findViewById(R.id.goalList);
        goalAdapter=new ArrayAdapter<goal>(this,android.R.layout.simple_list_item_1,theGoals);
        theDisplay.setAdapter(goalAdapter);

        //These are for creating a dialog for deleting/editing goals

        //YOU STILL NEED TO FINISH THE XML FILE SHOWN IN THE INSTRUCTIONS:
        //http://www.worldbestlearningcenter.com/tips/Android-dialog-ListView.htm
        theDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View theView, int position, long id){
                //After an item is clicked on, a dialog window will appear
               /* ViewGroup thatViewGroup = (ViewGroup) theView;
                TextView theTxt = (TextView) thatViewGroup.findViewById(R.id.theTxtItem);
                Toast.makeText(SavingsTrackerActivity.this,theTxt.getText().toString(),Toast.LENGTH_LONG).show();
           */ }
        });
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

        deletBtn.setOnClickListener(goalDeleter);
    }
    public void displayListDialog(View theView){
        AlertDialog.Builder theBuilder = new AlertDialog.Builder(SavingsTrackerActivity.this);
        theBuilder.setCancelable(true);
        theBuilder.setView(theDisplay);
        AlertDialog listDialog = theBuilder.create();
        listDialog.show();
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
            Toast.makeText(this,"You need to select a goal for deletion", Toast.LENGTH_LONG).show();
        }
    }
    public void addGoal(View theView)   {
        Iterator<goal> goalIterator = theGoals.iterator();

        while(goalIterator.hasNext()) {
            //This loop is to make sure the goal name has not been used more than once
            goal currentGoal = goalIterator.next();
            if(currentGoal.goalName.equals(name_et.getText().toString())) {
                Toast.makeText(this, "This goal name has already been used, please choose another", Toast.LENGTH_LONG).show();
                return; //returns if the name has been used already
            }
        }
        if(name_et.getText().toString().length() > 30) {//Checking the length of the goal name
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
                    currentVal = Float.parseFloat(current_et.getText().toString());
                goal holdGoal = new goal(Float.parseFloat(goal_et.getText().toString()), currentVal, name_et.getText().toString());
                if(holdGoal.getCurrentAmount() >= holdGoal.getGoalAmount()) {
                    Toast.makeText(this, "The current amount you entered is equal to the goal amount, you don't need to make a goal", Toast.LENGTH_LONG).show();
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



