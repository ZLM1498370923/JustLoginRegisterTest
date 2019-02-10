package com.example.justloginregistertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    private static final String databasename = "Mybook";
    private static final String tablename = "MyBook";
    private static final int version = 1;

    public Helper(Context context) {

        super(context, databasename, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String sqlstr = "create table " + tablename
                + "( book_id varchar(4) primary key , "
                + "book_name varchar(20) ," + "book_Ename varchar(20) )";

        db.execSQL(sqlstr);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        String sqlstr = "drop table IF EXISTS " + tablename;
        db.execSQL(sqlstr);
        this.onCreate(db);
    }

}
