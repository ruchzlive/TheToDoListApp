package com.cit.thetodolistapp.models;

public class ToDoItem {
    public String mSummery;
    public String mDescription;

    public ToDoItem(){ }

    public ToDoItem(String summery, String description) {
        this.mSummery = summery;
        this.mDescription = description;
    }

    public String getSummery() {
        return mSummery;
    }

    public void setSummery(String summery) {
        this.mSummery = summery;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
