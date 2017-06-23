package com.example.douzi.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douzi on 2017/6/23.
 */

public class PagerFlowLayout extends FlowLayout {

    private List<View> mChildren;
    private int indexCur = 0;
    private boolean isReset;

    public PagerFlowLayout(Context context) {
        super(context);
    }

    public PagerFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViews(List<View> children) {
        removeAllViews();
        mChildren = children;
        indexCur = 0;
        isReset = true;
        for (View child : children) {
            addViewInLayout(child, -1, null);
        }
        requestLayout();
    }

    public void nextPage() {
        int visualNum = getVisualViewNum();

        if (visualNum <= 0) {
            // TODO: 2017/6/23 不应该的逻辑
            return;
        }

        if (isReset) {
            indexCur+= visualNum;
            isReset = false;
        }

        removeViewsInLayout(0, visualNum);

        for (int i = 0; i < visualNum; i++) {
            addViewInLayout(nextChild(), -1, null);
        }
        requestLayout();
    }

    private View nextChild() {
        int size = mChildren.size();

        if (indexCur >= size) {
            indexCur = indexCur % size;
        }

        if (indexCur < 0) {
            indexCur += size;
        }

        return mChildren.get(indexCur);
    }
}
