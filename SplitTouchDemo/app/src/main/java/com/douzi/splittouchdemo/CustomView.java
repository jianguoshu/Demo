package com.douzi.splittouchdemo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created on 2017/1/19.
 */

public class CustomView extends LinearLayout {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("douzi-CustomView", ev.toString());
        Log.i("douzi-CustomView", "ev.getActionIndex() : " + ev.getActionIndex());
        Log.i("douzi-CustomView", "ev.getPointerId() : " + ev.getPointerId(ev.getActionIndex()));
        Log.i("douzi-CustomView", "ev.getPointerCount() : " + ev.getPointerCount());

        return super.dispatchTouchEvent(ev);
    }
}
