package com.example.hammadhanif.finance_tracker;

public class goal {
    //An object specifically created for this list
    float goalAmount; //Holds amount to be reached for the goal to be met
    float currentAmount;//Holds the current amount
    boolean reached;//"true" if goal has been reached, "false" otherwise
    String goalName;//Name of the goal

    public goal(){
        super();
    }
    public goal(float theGoal, float theCurrent, String theName) {
        super();
        this.goalAmount = theGoal;
        this.currentAmount = theCurrent;
        this.goalName = theName;
        this.reached = false;
    }

    //Getters and setters below
    public float getCurrentAmount() {
        return this.currentAmount;
    }
    public float getGoalAmount() {return this.goalAmount;}
    public void setCurrentAmount(float currentAmount) {
        this.currentAmount = currentAmount;
    }
    public void setReached() {
        if(this.goalAmount <= this.currentAmount){
            this.reached=true;
        }
    }
    @Override
    public String toString() {
        String isItMet = "Not Met";
        this.setReached();
        if(this.reached == true){isItMet = "Goal Met";}
        return this.goalName + ": "+isItMet+"\n\tGoal [$"+String.format("%.2f",this.goalAmount)+"]\n\tCurrently [$" + String.format("%.2f",this.currentAmount)+"]";
    }
}
