package com.example.hammadhanif.finance_tracker;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;
import static org.junit.Assert.*;



/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SavingTrackerActivityTest{


    @Test
    public void addGoalTest() {
        //Tests to see if same goals get added
        goal holdGoal = new goal(100.00f,0.00f,"TESTNAME");
        SavingsTrackerActivity theActiv = new SavingsTrackerActivity();

        theActiv.forAddGoalTest("TESTNAME","100.00","0.00");
        theActiv.forAddGoalTest("TESTNAME","10.00","0.00");

        assertEquals(theActiv.theGoals.size(),1);
    }
    @Test
    public void noAddTest() {
        //Tests to see if goals with the wrong format don't get added
        SavingsTrackerActivity theActiv = new SavingsTrackerActivity();

        theActiv.forAddGoalTest("TOO LONG111111111111111111111111111111111111","100.00","0.00");
        //Inserts a name that is too long into the addgoal

        theActiv.forAddGoalTest("NORMAL NAME","10.00","100.00");
        //Tries to add a goal where the current current is larger than the goal amount


        //None of the goals should have been added
        assertEquals(theActiv.theGoals.size(), 0);
    }

}
