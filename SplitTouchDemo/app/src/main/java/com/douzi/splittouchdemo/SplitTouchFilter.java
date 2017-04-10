package com.douzi.splittouchdemo;

import android.view.MotionEvent;

/**
 * Created on 2017/2/13.
 */

public class SplitTouchFilter {
    private boolean isNeedFilter = false;
    private boolean isTouchEnd = false;
    private int idValid = 0;
    private float xValidLast = 0;
    private float yValidLast = 0;

    public boolean isNeedFilter(MotionEvent event) {
        int mask = event.getActionMasked();
        int index = event.getActionIndex();
        int id = event.getPointerId(index);
        switch (mask) {
            case MotionEvent.ACTION_DOWN: // reset
                isNeedFilter = false;
                isTouchEnd = false;
                idValid = id;
                xValidLast = event.getX();
                yValidLast = event.getY();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (isTouchEnd) {
                    isNeedFilter = false;
                    isTouchEnd = false;
                    idValid = id;
                    xValidLast = event.getX(event.findPointerIndex(id));
                    yValidLast = event.getY(event.findPointerIndex(id));
                    event.setAction(MotionEvent.ACTION_DOWN);
                } else {
                    isNeedFilter = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (id == idValid) {
                    isNeedFilter = false;
                    isTouchEnd = true;
                    event.setAction(MotionEvent.ACTION_UP);
                } else {
                    isNeedFilter = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isTouchEnd) {
                    isNeedFilter = true;
                } else {
                    isNeedFilter = false;
                    if (event.getPointerCount() > 1) {
                        int indexValid = event.findPointerIndex(idValid);
                        float xValid = event.getX(indexValid);
                        float yValid = event.getY(indexValid);
                        if (xValid == xValidLast && yValid == yValidLast) {
                            isNeedFilter = true;
                        } else {
                            event.setLocation(xValid, yValid);
                            xValidLast = xValid;
                            yValidLast = yValid;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            default:
                if (isTouchEnd) {
                    isNeedFilter = true;
                } else {
                    isNeedFilter = false;
                }
                break;
        }
        return isNeedFilter;
    }
}
