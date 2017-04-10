package com.douzi.activitylifecycledemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("douzi", "SecondActivity - onCreate");

        View view = this.findViewById(R.id.tv_text);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("douzi", "SecondActivity - onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("douzi", "SecondActivity - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("douzi", "SecondActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("douzi", "SecondActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("douzi", "SecondActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("douzi", "SecondActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("douzi", "SecondActivity - onDestroy");
    }
}
