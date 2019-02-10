package com.example.justloginregistertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadBookActivity extends AppCompatActivity {

    private static final int images[] = new int[]{R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};

    private String texts[] = null;

    private GridView gridview = null;

    private static final String book_id[] = new String[]{"1", "2", "3", "4",
            "5", "6"};
    private static final String book_name[] = new String[]{"平凡的世界", "人生",
            "哲学", "陪安东尼度过曼长的黑夜", "那些都是你给我的爱", "菜根谭"};

    private static final String book_Ename[] = new String[]{"aa", "bb", "cc",
            "dd", "ee", "ff"};

    Dboperation dbo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        dbo = new Dboperation(this);
        gridview = (GridView) super.findViewById(R.id.gridview);

        if (dbo.count() == 0) {

            init();
        }

        gettexts();

        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();

        for (int i = 0; i < dbo.count(); i++) {

            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("itemImage", images[i]);
            map.put("itemText", texts[i]);

            lstImageItem.add(map);

        }

        SimpleAdapter adapter = new SimpleAdapter(this, lstImageItem,
                R.layout.six_book, new String[]{"itemImage", "itemText"},
                new int[]{R.id.ItemImage, R.id.ItemText});

        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new listrener());

        gridview.setOnItemLongClickListener(new longlistener());

    }

    private class listrener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long rowid) {
            // TODO Auto-generated method stub

            Intent it = new Intent(ReadBookActivity.this,
                    ShowBookActivity.class);

            HashMap<String, Object> item = (HashMap<String, Object>) parent
                    .getItemAtPosition(position);

            String itemtext = (String) item.get("itemText");

            it.putExtra("itemtext", itemtext);

            startActivity(it);

        }

    }

    public class longlistener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                       final int position, long rowid) {
            // TODO Auto-generated method stub

            AlertDialog.Builder log = new AlertDialog.Builder(ReadBookActivity.this);

            log.setMessage("确定要删除吗？").setTitle("删除")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            HashMap<String, Object> item = (HashMap<String, Object>) parent
                                    .getItemAtPosition(position);

                            String itemtext = (String) item.get("itemText");

                            dbo.delete(itemtext);

                            Toast.makeText(ReadBookActivity.this, "删除成功",
                                    Toast.LENGTH_SHORT).show();

                            Intent it = new Intent(ReadBookActivity.this,
                                    ReadBookActivity.class);

                            startActivity(it);
                            finish();

                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                    Toast.makeText(ReadBookActivity.this, "已取消",
                            Toast.LENGTH_SHORT).show();

                }
            }).show();
            return false;
        }
    }

    private void gettexts() {

        Dboperation dbo = new Dboperation(this);

        List<Book> books = new ArrayList<Book>();

        books = dbo.selectAll();

        texts = new String[books.size()];

        for (int i = 0; i < books.size(); i++) {

            Book book = books.get(i);

            texts[i] = book.get_name();

        }

    }

    private void init() {

        Dboperation dbo = new Dboperation(this);

        for (int i = 0; i < book_id.length; i++) {

            Book book = new Book(book_id[i], book_name[i], book_Ename[i]);
            dbo.insert(book);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.read_book, menu);

        return true;
    }
}
