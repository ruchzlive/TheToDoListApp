package com.cit.thetodolistapp.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.cit.thetodolistapp.database.DatabaseHandler;
import com.cit.thetodolistapp.models.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {

    private static ToDoListRepository instance;
    private ArrayList<ToDoItem> dataSet = new ArrayList<>();
    private DatabaseHandler db;

    private ToDoListRepository(Application application){
        db = new DatabaseHandler(application);
    }

    public static ToDoListRepository getInstance(Application application){
        if (instance == null){
            instance = new ToDoListRepository(application);
        }
        return instance;
    }

    public MutableLiveData<List<ToDoItem>> getToDoListItems(){
        setToDoListItems();
        MutableLiveData<List<ToDoItem>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    public void addToDoItem(ToDoItem toDoItem) {
        db.addToDoItem(toDoItem);
    }

    private void setToDoListItems(){

        List<ToDoItem> items = db.getAllToDoItems();
        if (items != null && items.size() != 0) {
            dataSet = new ArrayList<>(items);
        }

        /*dataSet.add(
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
        );*/

    }


}
