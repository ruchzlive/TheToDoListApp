package com.cit.thetodolistapp.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.cit.thetodolistapp.models.ToDoItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ToDoListFirebaseRepo {

    private static ToDoListFirebaseRepo instance;
    private ArrayList<ToDoItem> dataSet = new ArrayList<>();
    private MutableLiveData<List<ToDoItem>> mToDoItems = new MutableLiveData<>();

    private ToDoListFirebaseRepo() {}

    public static ToDoListFirebaseRepo getInstance(){
        if (instance == null){
            instance = new ToDoListFirebaseRepo();
        }
        return instance;
    }

    public MutableLiveData<List<ToDoItem>> getAllToDoItems(){
        loadToDoItems();
        mToDoItems.setValue(dataSet);
        return mToDoItems;
    }

    private void loadToDoItems() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("todoitems");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    dataSet.add(snapshot.getValue(ToDoItem.class));
                }
                mToDoItems.postValue(dataSet);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addToDoItem(ToDoItem item) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("todoitems");

        String id = reference.push().getKey();
        reference.child(id).setValue(item);
    }

}
