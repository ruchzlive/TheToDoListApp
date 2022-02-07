package com.cit.thetodolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cit.thetodolistapp.adapters.ToDoListAdapter;
import com.cit.thetodolistapp.models.ToDoItem;
import com.cit.thetodolistapp.repository.ToDoListRepository;
import com.cit.thetodolistapp.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ToDoListAdapter mToDoListAdapter;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvToDoList);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mViewModel.Init();

        mViewModel.getToDoListItems().observe(this, new Observer<List<ToDoItem>>() {
            @Override
            public void onChanged(List<ToDoItem> items) {
                mToDoListAdapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        mToDoListAdapter = new ToDoListAdapter(mViewModel.getToDoListItems().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mToDoListAdapter);
    }
}