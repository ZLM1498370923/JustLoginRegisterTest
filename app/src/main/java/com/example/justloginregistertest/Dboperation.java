package com.example.justloginregistertest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Dboperation {

    private static final String tablename = "MyBook";

    public SQLiteOpenHelper helper;
    public SQLiteDatabase db;

    public Dboperation(Context context) {

        helper = new Helper(context);
    }

    public void insert(Book book) {

        db = helper.getWritableDatabase();

        String sqlstr = "insert into " + tablename
                + "(book_id,book_name,book_Ename) values(?,?,?)";

        db.execSQL(
                sqlstr,
                new Object[]{book.get_id(), book.get_name(), book.get_Ename()});

    }

    public Book select(String book_name) {

        db = helper.getReadableDatabase();

        String sqlstr = "select * from " + tablename + " where book_name=?";

        Cursor cursor = db.rawQuery(sqlstr,
                new String[]{String.valueOf(book_name)});

        if (cursor.moveToNext()) {

            return new Book(cursor.getString(cursor.getColumnIndex("book_id")),
                    cursor.getString(cursor.getColumnIndex("book_name")),
                    cursor.getString(cursor.getColumnIndex("book_Ename")));
        }
        return null;

    }

    public List<Book> selectAll() {

        List<Book> books = new ArrayList<Book>();
        db = helper.getReadableDatabase();

        String sqlstr = "select * from " + tablename;
        Cursor cursor = db.rawQuery(sqlstr, null);

        while (cursor.moveToNext()) {

            books.add(new Book(cursor.getString(cursor
                    .getColumnIndex("book_id")), cursor.getString(cursor
                    .getColumnIndex("book_name")), cursor.getString(cursor
                    .getColumnIndex("book_Ename"))));
        }

        return books;

    }

    public long count() {

        db = helper.getReadableDatabase();

        String sqlstr = "select count(*) from " + tablename;

        Cursor cursor = db.rawQuery(sqlstr, null);

        if (cursor.moveToNext()) {

            return cursor.getLong(0);
        }
        return 0;

    }

    public void delete(String book_name) {

        db = helper.getWritableDatabase();

        String sqlstr = "delete from " + tablename + " where book_name='"
                + book_name + "'";

        db.execSQL(sqlstr);

    }
}
