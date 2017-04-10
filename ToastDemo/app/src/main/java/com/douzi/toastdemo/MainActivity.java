package com.douzi.toastdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Method;

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
        Toast toast = new Toast(this);
        try {
            Method method = toast.getClass().getMethod("getWindowParams");
            Object object = method.invoke(toast);
            if (object != null && object instanceof WindowManager.LayoutParams) {
                WindowManager.LayoutParams params = (WindowManager.LayoutParams) object;
                params.flags &= ~WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        View toastView = LayoutInflater.from(this).inflate(R.layout.toast_view, null);
        toastView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        toast.setView(toastView);
        toast.show();
    }
}
