package com.example.justloginregistertest;

public class Book {

    public String book_id;
    public String book_name;
    public String book_Ename;

    public Book(String book_id, String book_name, String book_Ename) {

        this.book_id = book_id;
        this.book_name = book_name;
        this.book_Ename = book_Ename;

    }

    public String get_id() {
        return book_id;

    }

    public String get_name() {
        return book_name;

    }

    public String get_Ename() {
        return book_Ename;

    }
}
