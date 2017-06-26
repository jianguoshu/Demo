package com.example.douzi.flowlayout;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by douzi on 2017/6/23.
 */

public class PagerFlowLayout extends FlowLayout {

    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;
    private List<View> mPageViews = new ArrayList<>();
    private int index = 0;
    private Adapter mAdapter;
    private PagerObserver mPagerObserver;

    public PagerFlowLayout(Context context) {
        super(context);
    }

    public PagerFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PagerFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
    }

    public void setAdapter(Adapter adapter) {
        if (mAdapter != null && mPagerObserver != null) {
            mAdapter.unregisterDataSetObserver(mPagerObserver);
        }
        mAdapter = adapter;
        if (mAdapter == null) {
            // TODO: 2017/6/26
        } else {
            // TODO: 2017/6/26
            if (mPagerObserver == null) {
                mPagerObserver = new PagerObserver() {
                    @Override
                    public void onDataSetChanged() {
                        // TODO: 2017/6/26
                    }
                };
            }
        }
    }

    public void nextPage() {
        Log.i(FlowLayoutHelper.class.getSimpleName(), "nextPage");


        removeAllViewsInLayout();
        mPageViews.clear();

        FlowLayoutHelper layoutHelper = new FlowLayoutHelper();
        FlowLayoutHelper.MeasureResult result = new FlowLayoutHelper.MeasureResult();
        layoutHelper.preMeasure(this, mWidthMeasureSpec, mHeightMeasureSpec, maxLine, result);

        View child = nextChild();
        while (layoutHelper.measure(child)) {
            mPageViews.add(child);
            child = nextChild();
        }

        layoutHelper.endMeasure();

        for (View view : mPageViews) {
            addViewInLayout(view, -1, view.getLayoutParams());
        }

        requestLayout();
        invalidate();
    }

    private View nextChild() {

        TextView view = new TextView(getContext());
        view.setSingleLine();
        view.setLayoutParams(new LayoutParams(480, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setEllipsize(TextUtils.TruncateAt.END);
        if (index >= 25) {
            index = 0;
        }

        view.setText(" " + index + " 测试以下呀");
        index++;

        return view;
    }

    public static abstract class Adapter {
        private final PagerObservable mDataSetObservable = new PagerObservable();

        public void registerDataSetObserver(PagerObserver observer) {
            mDataSetObservable.registerObserver(observer);
        }

        public void unregisterDataSetObserver(PagerObserver observer) {
            mDataSetObservable.unregisterObserver(observer);
        }

        public boolean hasRegister(PagerObserver observer) {
            return mDataSetObservable.hasRegister(observer);
        }

        public void notifyDataSetChanged() {
            mDataSetObservable.notifyDataSetChanged();
        }

        public abstract int getCount();

        public abstract View getView(int position);

        public abstract int getItemViewType(int position);

        public abstract int getViewTypeCount();
    }

    public static class PagerObservable extends android.database.Observable<PagerObserver> {

        public void notifyDataSetChanged() {
            synchronized (mObservers) {
                for (int i = mObservers.size() - 1; i >= 0; i--) {
                    mObservers.get(i).onDataSetChanged();
                }
            }
        }

        public boolean hasRegister(PagerObserver observer) {
            synchronized (mObservers) {
                return mObservers.contains(observer);
            }
        }
    }


    public interface PagerObserver {
        void onDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClicked(int posittion);
    }
}
