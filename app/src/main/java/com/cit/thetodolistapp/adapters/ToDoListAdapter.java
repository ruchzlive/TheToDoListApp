package com.cit.thetodolistapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cit.thetodolistapp.R;
import com.cit.thetodolistapp.models.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ToDoItem> mToDoItems;

    public ToDoListAdapter(List<ToDoItem> items){
        mToDoItems = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todoitem,
                parent, false);
        return new MyViewHolder(view);
    }

    public void addToDoList(List<ToDoItem> list) {
        mToDoItems = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.mTextViewSummery.setText(mToDoItems.get(position).getSummery());
        myViewHolder.mTextViewDesc.setText(mToDoItems.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mToDoItems != null ? mToDoItems.size() : 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextViewSummery;
        private TextView mTextViewDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewSummery = itemView.findViewById(R.id.textViewSummery);
            mTextViewDesc = itemView.findViewById(R.id.textViewDesc);
        }
    }
}
