package com.cit.thetodolistapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cit.thetodolistapp.models.ToDoItem;
import com.cit.thetodolistapp.repository.ToDoListRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    public ToDoListRepository mRepository;
    public MutableLiveData<List<ToDoItem>> mToDoItemsList;

    public void Init(Application application) {
        mRepository = ToDoListRepository.getInstance(application);
    }

    public LiveData<List<ToDoItem>> getToDoListItems() {
        if (mToDoItemsList == null){
            mToDoItemsList = mRepository.getToDoListItems();
        }
        return mToDoItemsList;
    }

    public void addToDoItem(final ToDoItem toDoItem) {
        List<ToDoItem> toDoItemList = mToDoItemsList.getValue();
        toDoItemList.add(toDoItem);
        mRepository.addToDoItem(toDoItem);
        mToDoItemsList.postValue(toDoItemList);
    }
}
