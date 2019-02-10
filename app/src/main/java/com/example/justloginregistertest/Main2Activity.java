package com.example.justloginregistertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @BindView(R.id.biji)
    Button biji;

    @BindView(R.id.return1)
    Button return1;

    @OnClick({
            R.id.biji,

            R.id.return1,

    })

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.biji:
                Intent intent = new Intent(this, NoteActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.return1:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                finish();
                break;

        }
    }
}