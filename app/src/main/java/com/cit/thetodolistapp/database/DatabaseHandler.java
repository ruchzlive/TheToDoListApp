package com.cit.thetodolistapp.database;

import static com.cit.thetodolistapp.database.ConstDef.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.cit.thetodolistapp.models.ToDoItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TODO_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TODO_ITEM_TABLE);
        onCreate(db);
    }

    public void addToDoItem(ToDoItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ToDoEntry.COLUMN_SUMMERY, item.getSummery());
        values.put(ToDoEntry.COLUMN_DESCRIPTION, item.getDescription());

        db.insert(ToDoEntry.TABLE_NAME, null, values);
        //insert into todo_item (summery, description) values ('sum 1', 'Description 1');
        db.close();
    }

    public List<ToDoItem> getAllToDoItems() {
        List<ToDoItem> itemList = new ArrayList<>();

        String selectQuery = "select * from " + ToDoEntry.TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                ToDoItem item = new ToDoItem();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setSummery(cursor.getString(1));
                item.setDescription(cursor.getString(2));
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        return itemList;
    }


    public ToDoItem getToDoItemById(int id) {
        ToDoItem item = null;

        String selectQuery = "select * from " + ToDoEntry.TABLE_NAME + " where _id = "  + String.valueOf(id);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                item = new ToDoItem();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setSummery(cursor.getString(1));
                item.setDescription(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        return item;
    }
}
