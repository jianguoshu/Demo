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

    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;
    private List<View> mPageViews = new ArrayList<>();
    private int position = 0;
    private int itemCount = 0;
    private Adapter mAdapter;
    private PagerObserver mPagerObserver;

    private OnItemClickListener onItemClickListener;
    private boolean needNextPage;
    private boolean hasMeasureStart;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

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
        hasMeasureStart = true;
        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
        if (isNeedNextPage()) {
            nextPageWithoutLayout();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setAdapter(Adapter adapter) {
        if (mAdapter != null && mPagerObserver != null) {
            mAdapter.unregisterDataSetObserver(mPagerObserver);
        }
        mAdapter = adapter;
        if (mAdapter == null) {
            position = 0;
            itemCount = 0;
            mPageViews.clear();
            removeAllViews();
        } else {
            position = 0;
            itemCount = adapter.getCount();
            if (mPagerObserver == null) {
                mPagerObserver = new PagerObserver() {
                    @Override
                    public void onDataSetChanged() {
                        position -= mPageViews.size();
                        nextPage();
                    }
                };
            }
            mAdapter.registerDataSetObserver(mPagerObserver);
            nextPage();
        }
    }

    public void nextPage() {

        nextPageWithoutLayout();

        requestLayout();
        invalidate();
    }

    private boolean isNeedNextPage(){
        if (needNextPage) {
            if (hasMeasureStart) {
                needNextPage = false;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean checkNextPageable() {
        if (!hasMeasureStart) {
            needNextPage = true;
            return false;
        } else {
            return true;
        }
    }

    private void nextPageWithoutLayout() {

        if (!checkNextPageable()) {
            return;
        }

        removeAllViewsInLayout();
        mPageViews.clear();

        if (itemCount <= 0) {
            return;
        }

        FlowLayoutHelper layoutHelper = new FlowLayoutHelper();
        FlowLayoutHelper.MeasureResult result = new FlowLayoutHelper.MeasureResult();
        layoutHelper.preMeasure(this, mWidthMeasureSpec, mHeightMeasureSpec, maxLine, result);

        View child = nextChild();
        while (layoutHelper.measure(child)) {
            mPageViews.add(child);
            child = nextChild();
        }

        layoutHelper.endMeasure();

        if (result.invalidChildNum > 0) {
            position -= result.invalidChildNum;
        }

        for (View view : mPageViews) {
            addViewInLayout(view, -1, view.getLayoutParams());
        }
    }

    private View nextChild() {

        if (position >= itemCount) {
            position = position % itemCount;
        }

        while (position < 0) {
            position += itemCount;
        }

        final int positionCur = position;
        position++;

        View view = mAdapter.getView(positionCur);
        if (view.getLayoutParams() == null) {
            view.setLayoutParams(generateDefaultLayoutParams());
        }
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClicked(positionCur);
                }
            }
        });
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
