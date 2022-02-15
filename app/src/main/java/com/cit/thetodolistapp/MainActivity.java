package com.cit.thetodolistapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cit.thetodolistapp.adapters.ToDoListAdapter;
import com.cit.thetodolistapp.models.ToDoItem;
import com.cit.thetodolistapp.repository.ToDoListRepository;
import com.cit.thetodolistapp.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ToDoListAdapter mToDoListAdapter;
    private MainActivityViewModel mViewModel;
    private FloatingActionButton mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rvToDoList);
        mAddButton = findViewById(R.id.floatingActionButton);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mViewModel.Init(getApplication());

        mViewModel.getToDoListItems().observe(this, new Observer<List<ToDoItem>>() {
            @Override
            public void onChanged(List<ToDoItem> items) {
                mToDoListAdapter.addToDoList(items);
                mToDoListAdapter.notifyDataSetChanged();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mViewModel.addToDoItem(new ToDoItem("Sample To Do Summery",
                //       "Sample To DO Description"));
                createNewPopupView();
            }
        });

        initRecyclerView();
    }

    private void createNewPopupView() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.popup_window, null);
        builder.setView(popupView);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button btnSave = popupView.findViewById(R.id.btnSave);
        EditText txtSummery = popupView.findViewById(R.id.editTextSummery);
        EditText txtDescription = popupView.findViewById(R.id.editTextDesc);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.addToDoItem(new ToDoItem(txtSummery.getText().toString(),
                        txtDescription.getText().toString()));
                alertDialog.hide();
            }
        });

    }

    private void initRecyclerView(){
        mToDoListAdapter = new ToDoListAdapter(mViewModel.getToDoListItems().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mToDoListAdapter);
    }
}