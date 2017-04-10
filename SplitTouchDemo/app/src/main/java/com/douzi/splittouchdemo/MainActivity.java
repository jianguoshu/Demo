package com.douzi.splittouchdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int mask = ev.getActionMasked();
        switch (mask) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                return true;
            default:
                return super.dispatchTouchEvent(ev);
        }

//        if (mSplitTouchFilter.isNeedFilter(ev)) {
////            Log.i("douzi-isNeedFilter", ev.toString());
////            Log.i("douzi-isNeedFilter", "ev.getActionIndex() : " + ev.getActionIndex());
////            Log.i("douzi-isNeedFilter", "ev.getPointerId() : " + ev.getPointerId(ev.getActionIndex()));
////            Log.i("douzi-isNeedFilter", "ev.getPointerCount() : " + ev.getPointerCount());
//        } else {
//            Log.i("douzi", ev.toString());
//            Log.i("douzi", "ev.getActionIndex() : " + ev.getActionIndex());
//            Log.i("douzi", "ev.getPointerId() : " + ev.getPointerId(ev.getActionIndex()));
//            Log.i("douzi", "ev.getPointerCount() : " + ev.getPointerCount());
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.findViewById(R.id.tv_one).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("douzi-tv_one", event.toString());
                Log.i("douzi-tv_one", "ev.getActionIndex() : " + event.getActionIndex());
                Log.i("douzi-tv_one", "ev.getPointerId() : " + event.getPointerId(event.getActionIndex()));
                Log.i("douzi-tv_one", "ev.getPointerCount() : " + event.getPointerCount());
                return true;
            }
        });
        this.findViewById(R.id.tv_two).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("douzi-tv_two", event.toString());
                Log.i("douzi-tv_two", "ev.getActionIndex() : " + event.getActionIndex());
                Log.i("douzi-tv_two", "ev.getPointerId() : " + event.getPointerId(event.getActionIndex()));
                Log.i("douzi-tv_two", "ev.getPointerCount() : " + event.getPointerCount());
                return true;
            }
        });
    }


}
