package com.cit.thetodolistapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.cit.thetodolistapp.models.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {

    private static ToDoListRepository instance;
    private ArrayList<ToDoItem> dataSet = new ArrayList<>();

    private ToDoListRepository(){}

    public static ToDoListRepository getInstance(){
        if (instance == null){
            instance = new ToDoListRepository();
        }
        return instance;
    }

    public MutableLiveData<List<ToDoItem>> getToDoListItems(){
        setToDoListItems();
        MutableLiveData<List<ToDoItem>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setToDoListItems(){
        dataSet.add(
                new ToDoItem("Go to market",
                        "Get vegetable and fruit.")
        );
        dataSet.add(
                new ToDoItem("Insurance Premium",
                        "Pay insurance premium of SGD 1200")
        );
        dataSet.add(
                new ToDoItem("Tickets for Japan tour",
                        "Reserve air tickets for japan tour.")
        );
        dataSet.add(
                new ToDoItem("Drop off cloths for dry cleaning",
                        "Drop all cloths to new laundry in the building")
        );

    }


}
