package com.cit.thetodolistapp.database;

import android.provider.BaseColumns;

public final class ConstDef {
    private ConstDef() {}

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDoDataStore.db";

    public static final String SQL_CREATE_TODO_ITEM_TABLE =
            "CREATE TABLE " + ToDoEntry.TABLE_NAME + " ( " +
                    ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ToDoEntry.COLUMN_SUMMERY + " TEXT, " +
                    ToDoEntry.COLUMN_DESCRIPTION + " TEXT ) ";



    public static final String SQL_DROP_TODO_ITEM_TABLE =
            "DROP TABLE IF EXISTS " + ToDoEntry.TABLE_NAME;

    public static class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo_item";
        public static final String COLUMN_SUMMERY = "summery";
        public static final String COLUMN_DESCRIPTION = "description";
    }


    //select *
    //form todo_item , todo_audit_info audit
    //where todo_item._id = todo_audit_info.item_id;
}
