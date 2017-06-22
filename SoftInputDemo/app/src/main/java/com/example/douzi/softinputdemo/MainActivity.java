package com.example.douzi.softinputdemo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View checkView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = this.findViewById(R.id.et);
        checkView = this.findViewById(R.id.tv_check);
        checkView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Rect rect = new Rect();
        v.getWindowVisibleDisplayFrame(rect);
        Log.i("douzi", rect.toString());
        Log.i("douzi", "visiable height : " + (rect.bottom - rect.top));
        View contentView = this.findViewById(android.R.id.content);
        int contentHeight = contentView.getHeight();
        Log.i("douzi", "contentHeight : " + contentHeight);
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Log.i("douzi", "isActive : " + imm.isActive());
        Log.i("douzi", "isActive : " + imm.isActive(view));
        Log.i("douzi", "isAcceptingText : " + imm.isAcceptingText());
        Log.i("douzi", "isFullscreenMode : " + imm.isFullscreenMode());
        Log.i("douzi", "isWatchingCursor : " + imm.isWatchingCursor(view));

    }
}
