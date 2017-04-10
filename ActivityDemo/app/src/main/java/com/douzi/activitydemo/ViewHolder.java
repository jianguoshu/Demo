package com.douzi.activitydemo;

import android.view.View;

/**
 * Created on 2017/1/19.
 */

public class ViewHolder {
    public View view;
    static ViewHolder holder = new ViewHolder();

    public static ViewHolder getHolder() {
        return holder;
    }
}
