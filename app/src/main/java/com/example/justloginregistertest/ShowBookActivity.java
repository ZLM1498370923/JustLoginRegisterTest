package com.example.justloginregistertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.page.BookAdapter;
import com.example.page.BookLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ShowBookActivity extends AppCompatActivity {

    private InputStream in;
    private int r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        BookLayout bk = new BookLayout(this);
        List<String> list = new ArrayList<String>();
        int t = 0;

        Dboperation dbo = new Dboperation(this);
        Intent it = super.getIntent();

        String itemtext = it.getStringExtra("itemtext");
        // title.setText(itemtext);
        setTitle(itemtext);

        Book book = dbo.select(itemtext);

        String book_Ename = book.get_Ename();

        // String filename = itemtext + ".txt";

        r = getresource(book_Ename);

        try {

            in = getResources().openRawResource(r);

            byte[] by = new byte[420];

            // byte[] by = new byte[3 * 140];

            while (t < in.available() / 420) {
                in.read(by);
                String str = new String(by, "UTF-8");
                list.add(str);
                t++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        BookAdapter ba = new BookAdapter(this);
        ba.addItem(list);
        bk.setPageAdapter(ba);
        setContentView(bk);


    }

    public int getresource(String book_Ename) {

        int r;

        if (book_Ename.equals("aa")) {

            r = R.raw.aa;

            return r;

        }
        if (book_Ename.equals("bb")) {

            r = R.raw.bb;

            return r;

        }
        if (book_Ename.equals("cc")) {

            r = R.raw.cc;

            return r;

        }
        if (book_Ename.equals("dd")) {

            r = R.raw.dd;

            return r;

        }
        if (book_Ename.equals("ee")) {

            r = R.raw.ee;

            return r;

        }
        if (book_Ename.equals("ff")) {

            r = R.raw.ff;

            return r;

        }
        return 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_book, menu);
        return true;

    }
}
