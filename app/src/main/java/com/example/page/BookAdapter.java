package com.example.page;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.justloginregistertest.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;

public class BookAdapter implements IAdapter {
    private List<String> strList = new ArrayList<String>();

    private Context mContext;

    public BookAdapter(Context context) {
        super();
        this.mContext = context;
    }

    public void addItem(List<String> list) {
        strList.addAll(list);
    }

    public int getCount() {
        return strList.size();
    }

    public String getItem(int position) {
        return strList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position) {
        TextView textView = new TextView(mContext);
        textView.setText(strList.get(position));
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(32);
        textView.setBackgroundColor(Color.WHITE);
        textView.setBackgroundResource(R.drawable.ly);
        textView.setPadding(10, 10, 10, 10);
        textView.setLayoutParams(new FrameLayout.LayoutParams(
                FILL_PARENT, FILL_PARENT));
        return textView;
    }
}
