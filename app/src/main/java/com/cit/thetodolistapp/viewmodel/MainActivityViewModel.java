package com.cit.thetodolistapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cit.thetodolistapp.models.ToDoItem;
import com.cit.thetodolistapp.repository.ToDoListRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public ToDoListRepository mRepository;
    public MutableLiveData<List<ToDoItem>> mToDoItemsList;

    public void Init() {
        mRepository = ToDoListRepository.getInstance();
    }

    public LiveData<List<ToDoItem>> getToDoListItems() {
        if (mToDoItemsList == null){
            mToDoItemsList = mRepository.getToDoListItems();
        }
        return mToDoItemsList;
    }
}
