package com.example.justloginregistertest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {
    private static final String databasename = "rearbook";
    private static final String tablename = "books";
    private static final int version = 1;
    private static final String column[] = new String[]{"book_id",
            "book_name", "address"};

    private static final String book_id[] = new String[]{"1", "2", "3", "4",
            "5", "6"};
    private static final String book_name[] = new String[]{"平凡的世界", "人生",
            "哲学", "陪安东尼度过曼长的黑夜", "那些都是你给我的爱", "菜根谭"};
    private static final String address = "D:/Program Files/AndroidProject/ReadBook/book/";

    public database(Context context) {
        super(context, databasename, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String sqlstr = "create table " + tablename
                + "( book_id varchar(4) primary key , "
                + "book_name varchar(20) ," + "address varchar(20) )";

        db.execSQL(sqlstr);

        for (int i = 0; i < 6; i++) {

            String str = "insert into " + tablename + " values('" + book_id[i]
                    + "','" + book_name[i] + "','" + address + "')";

            db.execSQL(str);

            System.out.println(book_id[i] + book_name[i] + address);

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        String sqlstr = "drop table IF EXISTS " + tablename;
        db.execSQL(sqlstr);
        this.onCreate(db);
    }

    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(tablename, column, null, null, null, null,
                null);
        return cursor;

    }

    public void insert(ContentValues values) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tablename, null, values);

    }

}
