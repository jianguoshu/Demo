package com.douzi.activitydemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (TextView) this.findViewById(R.id.text);
    }

    @Override
    protected void onStop() {
        Log.i("douzi", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("douzi", "onDestroy");
        super.onDestroy();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView textView = new TextView(MainActivity.this);
                PopupWindow popupWindow = new PopupWindow(textView, 200, 200);
                popupWindow.showAsDropDown(view);
            }
        }, 2000);

    }
}
