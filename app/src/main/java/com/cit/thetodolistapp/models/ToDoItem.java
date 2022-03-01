package com.cit.thetodolistapp.models;

public class ToDoItem {
    private int mId;
    private String mSummery;
    private String mDescription;

    public ToDoItem(){ }

    public ToDoItem(String summery, String description) {
        this.mSummery = summery;
        this.mDescription = description;
    }

    public int getId() { return mId; }

    public void setId(int id) { this.mId = id; }

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
