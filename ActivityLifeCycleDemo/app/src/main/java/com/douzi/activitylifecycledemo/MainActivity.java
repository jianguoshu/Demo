package com.douzi.activitylifecycledemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View view = this.findViewById(R.id.tv_text);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.i("douzi", "MainActivity - onCreate");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("douzi", "MainActivity - onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("douzi", "MainActivity - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("douzi", "MainActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("douzi", "MainActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("douzi", "MainActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("douzi", "MainActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("douzi", "MainActivity - onDestroy");
    }
}
